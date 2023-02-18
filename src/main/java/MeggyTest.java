import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import meggy.Meggy;
import meggy.MeggyTime;
import meggy.Resource;
import meggy.TaskList;
import meggy.exception.MeggyException;
import meggy.task.DdlTask;
import meggy.task.EventTask;
import meggy.task.TodoTask;
import meggy.task.UserTask;

/** For testing purpose only. */
public class MeggyTest {
    private static final Random RAND = new Random();
    /** Roughly 400 loops per second */
    private static final int N_LOOP = 60 * 400;
    private static final int N_CORE = Runtime.getRuntime().availableProcessors();
    private static final Consumer<String> DROP = s -> {
    };

    /** @return String that will never be entirely whitespace. */
    private static String randString() {
        final int strLenMax = 50;
        final int printableCharRange = 95;
        while (true) {
            final int len = 1 + RAND.nextInt(strLenMax);
            char[] s = new char[len];
            boolean isAllSpace = true;
            for (int i = 0; i < len; i++) {
                final char c = (char) (' ' + RAND.nextInt(printableCharRange));
                s[i] = c;
                isAllSpace &= c == ' ';
            }
            if (!isAllSpace) {
                return new String(s);
            }
        }
    }

    private static MeggyTime randMeggyTime() {
        return MeggyTime.of(RAND.nextBoolean() ? randString()
                : LocalDateTime.ofEpochSecond(RAND.nextInt(), 0, ZoneOffset.UTC).format(MeggyTime.OUT_FMT));
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
    public void todoTaskIntegrityTest() throws MeggyException {
        String s = randString();
        TodoTask a = new TodoTask(s);
        String data = a.recreateCmd();
        TodoTask b = new TodoTask(data.substring(data.indexOf(' ') + 1));
        assertEquals(a, b, s);
    }

    @Test
    public void ddlTaskIntegrityTest() throws MeggyException {
        String s = randString() + DdlTask.DUE_KEYWORD_FORMATTED + randMeggyTime();
        DdlTask a = DdlTask.of(s);
        String data = a.recreateCmd();
        DdlTask b = DdlTask.of(data.substring(data.indexOf(' ') + 1));
        assertEquals(a, b, s);
    }

    @Test
    public void eventTaskIntegrityTest() throws MeggyException {
        String s = randString() + EventTask.START_KEYWORD_FORMATTED + randMeggyTime() + EventTask.END_KEYWORD_FORMATTED
                + randMeggyTime();
        EventTask a = EventTask.of(s);
        String data = a.recreateCmd();
        EventTask b = EventTask.of(data.substring(data.indexOf(' ') + 1));
        assertEquals(a, b, s);
    }

    @Test
    public void bulkIntegrityTest() {
        final int nTest = N_LOOP * N_CORE;
        IntStream.range(0, nTest).parallel().forEach(iTest -> {
            try {
                todoTaskIntegrityTest();
                ddlTaskIntegrityTest();
                eventTaskIntegrityTest();
                meggyTimeIntegrityTest();
            } catch (MeggyException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void bulkStorageTest() {
        final int nTest = N_LOOP / 200 * N_CORE;
        IntStream.range(0, nTest).parallel().forEach(iTest -> {
            try {
                randStorageTest(iTest);
            } catch (MeggyException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public synchronized void randStorageTest() throws MeggyException {
        randStorageTest(-1);
    }

    private void randStorageTest(int iTest) throws MeggyException {
        final int listLenMax = 50;
        String storageFilePath = "test/" + iTest + ".txt";
        Meggy m1 = new Meggy(storageFilePath);
        m1.bindUi(DROP);

        final TaskList taskList = new TaskList();
        for (int i = RAND.nextInt(listLenMax); i >= 0; i--) {
            final String s;
            final UserTask task;
            final String line;
            switch (RAND.nextInt(3)) {
            case 1:
                s = randString() + DdlTask.DUE_KEYWORD_FORMATTED + randMeggyTime();
                task = DdlTask.of(s);
                line = Resource.CMD_DDL + ' ' + s;
                break;
            case 2:
                s = randString() + EventTask.START_KEYWORD_FORMATTED + randMeggyTime() + EventTask.END_KEYWORD_FORMATTED
                        + randMeggyTime();
                task = EventTask.of(s);
                line = Resource.CMD_EVENT + ' ' + s;
                break;
            default:
                s = randString();
                task = new TodoTask(s);
                line = Resource.CMD_TODO + ' ' + s;
            }
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
        Meggy m2 = new Meggy(storageFilePath);
        m2.bindUi(DROP);
        assert (m1.equals(m2));
        new File(storageFilePath).delete();
    }
}
