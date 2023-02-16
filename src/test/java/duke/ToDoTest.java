package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void addTest() {
        assertEquals("[T][ ] HELLO", new ToDo("HELLO").toString());
    }

    @Test
    public void addTest2() {
        assertEquals("[T][X] HELLO2", new ToDo("HELLO2", true).toString());
    }
}
