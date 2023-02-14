package luffy.task;

import java.time.LocalDate;

import luffy.exception.LuffyException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void toFileTest() {
        // test undone
        String expected = "D | 0 | toFileTest | 2023-01-01\n";
        Deadline d = new Deadline("toFileTest", LocalDate.parse("2023-01-01"));
        String actual = d.toFile();
        assertEquals(expected, actual);
        // test done
        expected = "D | 1 | toFileTest | 2023-01-01\n";
        d.markAsDone();
        actual = d.toFile();
        assertEquals(expected, actual);
    }

    @Test
    public void toDeadlineFromFileStrTest() {
        try {
            // test undone
            String expected = "[D][ ] toDeadlineFromFileStrTest (by: Jan 1 2023)";
            Deadline d = Deadline.toDeadlineFromFileStr("toDeadlineFromFileStrTest",
                    "0", "2023-01-01");
            String actual = d.toString();
            assertEquals(expected, actual);
            // test done
            expected = "[D][X] toDeadlineFromFileStrTest (by: Jan 1 2023)";
            d.markAsDone();
            actual = d.toString();
            assertEquals(expected, actual);
        } catch (LuffyException e) {
            fail();
        }
    }

    @Test
    public void toStringTest() {
        // test undone
        String expected = "[D][ ] toStringTest (by: Jan 1 2023)";
        Deadline d = new Deadline("toStringTest", LocalDate.parse("2023-01-01"));
        String actual = d.toString();
        assertEquals(expected, actual);
        // test done
        expected = "[D][X] toStringTest (by: Jan 1 2023)";
        d.markAsDone();
        actual = d.toString();
        assertEquals(expected, actual);
    }
}
