package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to define the tests for the Event class methods
 */
public class EventTest {

    /**
     * Testing the encode method of the Event class
     */
    @Test
    public void encodeTest(){
        assertEquals("E |   | meeting bob | 2021-03-15 | 2022-04-23",
                (new Event(false,
                        "event meeting bob /from 2021-03-15 /to 2022-04-23")).encode());
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }
}