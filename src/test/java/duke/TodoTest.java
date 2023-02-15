package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toStringTest() {
        Todo todoTest = new Todo("test", "NOT_DONE");
        assertEquals("[T][ ] test", todoTest.toString());
    }

    @Test
    public void toDataTest() {
        Todo todoTest = new Todo("test", "DONE");
        assertEquals("T|DONE|test", todoTest.toData());
    }

}
