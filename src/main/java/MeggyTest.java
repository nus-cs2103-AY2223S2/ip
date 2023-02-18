import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import meggy.MeggyTime;
import meggy.exception.MeggyException;
import meggy.task.DdlTask;
import meggy.task.EventTask;
import meggy.task.TodoTask;

/** For testing purpose only. */
public class MeggyTest {
    private static final Random RAND = new Random();
    private static final int N_TEST = 1000000;

    /** @return String that will never be entirely whitespace. */
    private static String randString() {
        final int strLenMax = 50;
        final int printableCharRange = 95;
        while (true) {
            final int len = 1 + RAND.nextInt(strLenMax);
            char[] s = new char[len];
            boolean allSpace = true;
            for (int i = 0; i < len; i++) {
                final char c = (char) (' ' + RAND.nextInt(printableCharRange));
                s[i] = c;
                allSpace &= c == ' ';
            }
            if (!allSpace) {
                return new String(s);
            }
        }
    }

    private static MeggyTime randMeggyTime() {
        final double randStrProb = 0.5;
        return MeggyTime.of(RAND.nextDouble() < randStrProb ? randString()
                : LocalDateTime.ofEpochSecond(RAND.nextInt(), 0, ZoneOffset.UTC).format(MeggyTime.ENCODE_FORMAT));
    }

    @Test
    public void todoTaskIntegrityTest() {
        IntStream.range(0, N_TEST).parallel().forEach(i -> {
            try {
                String s = randString();
                TodoTask a = new TodoTask(s);
                String data = a.recreateCmd();
                TodoTask b = new TodoTask(data.substring(data.indexOf(' ') + 1));
                assertEquals(a, b, s);
            } catch (MeggyException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void ddlTaskIntegrityTest() {
        IntStream.range(0, N_TEST).parallel().forEach(i -> {
            try {
                String s = randString() + DdlTask.DUE_KEYWORD_FORMATTED + randMeggyTime();
                DdlTask a = DdlTask.of(s);
                String data = a.recreateCmd();
                DdlTask b = DdlTask.of(data.substring(data.indexOf(' ') + 1));
                assertEquals(a, b, s);
            } catch (MeggyException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void eventTaskIntegrityTest() {
        IntStream.range(0, N_TEST).parallel().forEach(i -> {
            try {
                String s = randString() + EventTask.START_KEYWORD_FORMATTED + randMeggyTime()
                        + EventTask.END_KEYWORD_FORMATTED + randMeggyTime();
                EventTask a = EventTask.of(s);
                String data = a.recreateCmd();
                EventTask b = EventTask.of(data.substring(data.indexOf(' ') + 1));
                assertEquals(a, b, s);
            } catch (MeggyException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
