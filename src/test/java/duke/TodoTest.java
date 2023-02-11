package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to define tests for Todo class methods
 */
public class TodoTest {
    /**
     * Testing the encode method of the Todo class
     */
    @Test
    public void encodeTest(){
        assertEquals("T |   | borrow book",
                (new Todo(false, "todo borrow book")).encode());
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }
}