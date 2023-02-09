package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;



public class DeadlineTest {
    @Test
    public void testStringConversion() {
        Deadline example = new Deadline("description", "2019-10-10 1859");
        assertEquals("[D][ ] description (by: Oct 10 2019 6:59 PM)", example.toString());
    }

    @Test
    public void testDataConversion() {
        Deadline example = new Deadline("description", "2019-10-10 1859");
        assertEquals("D/0/description/2019-10-10 1859", example.printData());
    }

    @Test
    public void construtor_wrongDateFormat_exceptionThrown() {
        try {
            Deadline example = new Deadline("description", "2019/10/19 1859");
            fail();
        } catch (Exception e) {
            assertEquals(1, 1);
        }
    }
}
