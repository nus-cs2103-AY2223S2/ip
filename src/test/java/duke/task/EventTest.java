package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;



public class EventTest {
    @Test
    public void testStringConversion() {
        Event example = new Event("description", "2019-10-10 1859", "2019-10-15 0700");
        assertEquals("[E][ ] description (from: Oct 10 2019 6:59 PM to: Oct 15 2019 7:00 AM)", example.toString());
    }

    @Test
    public void testDataConversion() {
        Event example = new Event("description", "2019-10-10 1859", "2019-10-15 0700");
        assertEquals("E/0/description/2019-10-10 1859/2019-10-15 0700", example.printData());
    }

    @Test
    public void constructor_wrongDateFormat_exceptionThrown() {
        try {
            Event example = new Event("description", "2019/10/10 1859", "2019-10-15 0700");
            fail();
        } catch (Exception e) {
            assertEquals(1, 1);
        }

        try {
            Event example2 = new Event("description", "2019-10-10 1859", "2019/10/15 0700");
            fail();
        } catch (Exception e) {
            assertEquals(1, 1);
        }
    }
}
