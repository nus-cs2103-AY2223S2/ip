import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import meggy.Meggy;
import meggy.MeggyTime;
import meggy.Resource;
import meggy.TaskList;
import meggy.Util;
import meggy.exception.Function;
import meggy.exception.MeggyException;
import meggy.task.DdlTask;
import meggy.task.EventTask;
import meggy.task.UserTask;

/** For testing purpose only. */
public class MeggyTest {
    private static final Random RAND = new Random();
    /** Roughly 400 loops per second */
    private static final int N_LOOP = 120 * 400;
    private static final int N_CORE = Runtime.getRuntime().availableProcessors();
    private static final Consumer<String> DROP = s -> {
    };
    private static final File TEST_DIR = new File("test");

    private static final Supplier<String> todoInput = MeggyTest::randString;
    private static final Supplier<String> ddlInput = () -> {
        while (true) {
            final String desc = randString();
            if (!desc.startsWith(DdlTask.DUE_KEYWORD_FORMATTED)) {
                return desc + DdlTask.DUE_KEYWORD_FORMATTED + randMeggyTime();
            }
        }
    };
    private static final Supplier<String> eventInput = () -> {
        while (true) {
            final String desc = randString();
            if (!(desc.startsWith(EventTask.START_KEYWORD_FORMATTED)
                    || desc.startsWith(EventTask.END_KEYWORD_FORMATTED))) {
                return desc + EventTask.START_KEYWORD_FORMATTED + randMeggyTime() + EventTask.END_KEYWORD_FORMATTED
                        + randMeggyTime();
            }
        }
    };
    @SuppressWarnings("unchecked")
    private static final Supplier<String>[] userTaskRandInputs =
            (Supplier<String>[]) new Supplier<?>[]{todoInput, ddlInput, eventInput};
    private static final String[] userTaskCmds = new String[]{Resource.CMD_TODO, Resource.CMD_DDL, Resource.CMD_EVENT};
    @SuppressWarnings("unchecked")
    private static final Function<String, UserTask>[] newTasks =
            (Function<String, UserTask>[]) new Function<?, ?>[userTaskCmds.length];

    static {
        newTasks[0] = Util.TODO_NEW;
        newTasks[1] = Util.DDL_NEW;
        newTasks[2] = Util.EVENT_NEW;
        TEST_DIR.mkdir();
    }

    /** @return String that will never be entirely whitespace. */
    private static String randString() {
        final int strLenMax = 50;
        final int printableCharRange = 95;
        while (true) {
            final int len = 1 + RAND.nextInt(strLenMax);
            char[] s = new char[len];
            for (int i = 0; i < len; i++) {
                final char c = (char) (' ' + RAND.nextInt(printableCharRange));
                s[i] = c;
            }
            final String ans = new String(s).trim();
            if (!ans.isEmpty()) {
                return ans;
            }
        }
    }

    private static MeggyTime randMeggyTime() {
        return MeggyTime.of(RAND.nextBoolean() ? randString()
                : LocalDateTime.ofEpochSecond(RAND.nextInt(), 0, ZoneOffset.UTC).format(MeggyTime.OUT_FMT));
    }

    private static <T extends UserTask> void taskIntegrityTest(
            Supplier<String> randInput, Function<String, T> newTask) {
        final String s = randInput.get();
        try {
            final T a = newTask.apply(s);
            final String data = a.recreateCmd();
            final T b = newTask.apply(data.substring(data.indexOf(' ') + 1));
            assertEquals(a, b, s);
        } catch (MeggyException e) {
            throw new RuntimeException(s, e);
        }
    }

    private static void randStorageTest(File storageFile) throws MeggyException {
        final int listLenMax = 50;
        final Meggy m1 = new Meggy(storageFile);
        m1.bindUi(DROP);

        final TaskList taskList = new TaskList();
        for (int i = RAND.nextInt(listLenMax); i > 0; i--) {
            final int j = RAND.nextInt(userTaskCmds.length);
            final String s = userTaskRandInputs[j].get();
            final UserTask task = newTasks[j].apply(s);
            final String line = userTaskCmds[j] + ' ' + s;
            if (taskList.contains(task)) {
                continue;
            }
            taskList.add(task);
            m1.parseAndGetResponse(line);
        }
        final int len = taskList.size();
        for (int i = 0; i < len * 2; i++) {
            int idx = RAND.nextInt(len) + 1;
            final String line = (RAND.nextBoolean() ? Resource.CMD_MARK : Resource.CMD_UNMK) + ' ' + idx;
            m1.parseAndGetResponse(line);
        }
        final Meggy m2 = new Meggy(storageFile);
        m2.bindUi(DROP);
        assert (m1.equals(m2));
        storageFile.delete();
    }

    @Test
    public void meggyTimeIntegrityTest() {
        MeggyTime a = randMeggyTime();
        String s = a.toString();
        if (s.charAt(0) == '[') {
            int n = s.length();
            assertEquals(']', s.charAt(n - 1));
            s = s.substring(1, n - 1);
        }
        MeggyTime b = MeggyTime.of(s);
        assertEquals(a, b, s);
    }

    @Test
    public void todoTaskIntegrityTest() {
        taskIntegrityTest(todoInput, Util.TODO_NEW);
    }

    @Test
    public void ddlTaskIntegrityTest() {
        taskIntegrityTest(ddlInput, Util.DDL_NEW);
    }

    @Test
    public void eventTaskIntegrityTest() {
        taskIntegrityTest(eventInput, Util.EVENT_NEW);
    }

    @Test
    public void bulkIntegrityTest() {
        final int nTest = N_LOOP * N_CORE;
        IntStream.range(0, nTest).parallel().forEach(iTest -> {
            todoTaskIntegrityTest();
            ddlTaskIntegrityTest();
            eventTaskIntegrityTest();
            meggyTimeIntegrityTest();
        });
    }


    @Test
    public void bulkStorageTest() {
        final int nTest = N_LOOP / 200 * N_CORE;
        IntStream.range(0, nTest).parallel().forEach(iTest -> {
            try {
                randStorageTest(new File(TEST_DIR, iTest + ".txt"));
            } catch (MeggyException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void storageTest() throws MeggyException {
        randStorageTest(new File(TEST_DIR, ".txt"));
    }
}
