package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private final Todo todo1 = new Todo("some description");
    private final Todo todo2 = new Todo("another description");
    @Test
    public void printTask() {
        assertEquals(todo1.printTask(), "[T][ ] some description");
        todo2.markTaskDone(true);
        assertEquals(todo2.printTask(), "[T][X] another description");
    }

    @Test
    public void formatForFile() {
        assertEquals(todo1.formatForFile(), "T|O|some description");
        todo2.markTaskDone(true);
        assertEquals(todo2.formatForFile(), "T|X|another description");

    }
}
