package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to define the tests for the Deadline class methods
 */
public class DeadlineTest {

    /**
     * Testing the encode method of the Deadline class
     */
    @Test
    public void encodeTest(){
        assertEquals("D | X | return book | 2019-10-15",
                (new Deadline(true, "deadline return book /by 2019-10-15")).encode());
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }
}