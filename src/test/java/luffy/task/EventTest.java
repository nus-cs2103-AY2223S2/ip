package luffy.task;

import luffy.exception.LuffyException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void toFileTest() {
        // test for undone event
        String testExpected = "E | 0 | toFileTestStr | 2022-01-01 | 2023-12-12\n";
        Event e = new Event("toFileTestStr", LocalDate.parse("2022-01-01"),
                LocalDate.parse("2023-12-12"));
        String testActual = e.toFile();
        assertEquals(testExpected, testActual);
        // test for done event
        testExpected = "E | 1 | toFileTestStr | 2022-01-01 | 2023-12-12\n";
        e.markAsDone();
        testActual = e.toFile();
        assertEquals(testExpected, testActual);
    }

    @Test
    public void toEventFromFileStrTest() {
        Event eventExpected = new Event("toEventFromFileStrTest",
                LocalDate.parse("2022-01-01"), LocalDate.parse("2023-12-12"));
        try {
            // test for unDone event
            Event eventActual = Event.toEventFromFileStr("toEventFromFileStrTest", "0",
                    "2022-01-01", "2023-12-12");
            assertEquals(eventExpected.toString(), eventActual.toString());
            // test for done event
            eventExpected.markAsDone();
            eventActual = Event.toEventFromFileStr("toEventFromFileStrTest", "1",
                    "2022-01-01", "2023-12-12");
            assertEquals(eventExpected.toString(), eventActual.toString());
        } catch (LuffyException e) {
            fail();
        }
    }

    @Test
    public void toStringTest() {
        // test for undone event
        String expected = "[E][ ] toStringTest (from: Jan 1 2022 to: Dec 12 2023)";
        Event e = new Event("toStringTest", LocalDate.parse("2022-01-01"),
                LocalDate.parse("2023-12-12"));
        String actual = e.toString();
        assertEquals(expected, actual);
        // test for undone event
        expected = "[E][X] toStringTest (from: Jan 1 2022 to: Dec 12 2023)";
        e.markAsDone();
        actual = e.toString();
        assertEquals(expected, actual);
    }
}
