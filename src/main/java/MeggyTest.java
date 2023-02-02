import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

import org.junit.jupiter.api.Test;

import meggy.MeggyTime;
import meggy.exception.MeggyException;
import meggy.task.DdlTask;
import meggy.task.EventTask;
import meggy.task.TodoTask;

/** For testing purpose only. */
public class MeggyTest {
    private static final Random RAND = new Random();
    private static final int N_TEST = 1024;

    /** @return String that will never be entirely whitespace. */
    private static String randString() {
        while (true) {
            final int n = 1 + RAND.nextInt(50);
            char[] s = new char[n];
            boolean allSpace = true;
            for (int i = 0; i < n; i++) {
                final char c = (char) (' ' + RAND.nextInt(95));
                s[i] = c;
                allSpace &= c == ' ';
            }
            if (!allSpace) {
                return new String(s);
            }
        }
    }

    private static MeggyTime randMeggyTime() {
        return MeggyTime.of(RAND.nextDouble() < 0.5 ? randString()
                : LocalDateTime.ofEpochSecond(RAND.nextInt(), 0, ZoneOffset.UTC).format(MeggyTime.ENCODE_FORMAT));
    }

    @Test
    public void todoTaskIntegrityTest() throws MeggyException {
        for (int k = 0; k < N_TEST; k++) {
            TodoTask a = new TodoTask(randString());
            String data = a.encode();
            TodoTask b = new TodoTask(data.substring(data.indexOf(' ') + 1));
            assertEquals(a, b);
        }
    }

    @Test
    public void ddlTaskIntegrityTest() throws MeggyException {
        for (int k = 0; k < N_TEST; k++) {
            DdlTask a = DdlTask.of(randString() + DdlTask.DUE_KEYWORD_FORMATTED + randMeggyTime());
            String data = a.encode();
            DdlTask b = DdlTask.of(data.substring(data.indexOf(' ') + 1));
            assertEquals(a, b);
        }
    }

    @Test
    public void eventTaskIntegrityTest() throws MeggyException {
        for (int k = 0; k < N_TEST; k++) {
            EventTask a = EventTask.of(randString() + EventTask.START_KEYWORD_FORMATTED + randMeggyTime()
                    + EventTask.END_KEYWORD_FORMATTED + randMeggyTime());
            String data = a.encode();
            EventTask b = EventTask.of(data.substring(data.indexOf(' ') + 1));
            assertEquals(a, b);
        }
    }
}
