package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class EventTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    /**
     * Tests if the event toString method prints correctly.
     */
    @Test
    public void eventToStringTest_toStringOutput_success() {
        Event eve = new Event("Party", "10/2", "11/2");
        assertEquals("[E][ ] Party (from: 10/2 to: 11/2)", eve.toString());
    }



}
