package duke.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {

    ToDo toDoTask;

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
        assertEquals("[T][X] return book",toDoTask.toString());
    }

    @Test
    public void unmarkTaskTest() {
        toDoTask.markTask();
        toDoTask.unmarkTask();
        assertEquals("[T][ ] return book",toDoTask.toString());
    }

}
