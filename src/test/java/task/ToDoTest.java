package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void createToDoTest() {
        assertEquals("[T][] TASK DESCRIPTION", new ToDo("TASK DESCRIPTION").toString());
    }

    @Test
    public void saveToDoTest() {
        assertEquals("[T][] TASK DESCRIPTION", new ToDo("TASK DESCRIPTION").save());
    }
}