package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoTest {

    private ToDo toDoTask;

    @BeforeEach
    public void setUp() {
        toDoTask = new ToDo("todo return book");
    }

    @Test
    public void getTaskTest() {
        assertEquals("return book", toDoTask.getTask());
    }

    @Test
    public void markTaskTest() {
        toDoTask.markTask();
        assertEquals("[T][X] return book", toDoTask.toString());
    }

    @Test
    public void unmarkTaskTest() {
        toDoTask.markTask();
        toDoTask.unmarkTask();
        assertEquals("[T][ ] return book", toDoTask.toString());
    }

}
