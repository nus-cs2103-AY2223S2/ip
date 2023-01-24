package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    /**
     * Tests if the event toString method prints correctly.
     */
    @Test
    public void eventToStringTest(){
        Event eve = new Event("Party", "10/2", "11/2");
        assertEquals("[E][ ] Party (from: 10/2 to: 11/2)", eve.toString());
    }



}
