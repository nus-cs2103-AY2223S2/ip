package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testStringConversion() {
        String name = "tests";
        ToDo a = new ToDo(name);
        assertEquals("[T][ ] " + name, a.toString());
    }

}
