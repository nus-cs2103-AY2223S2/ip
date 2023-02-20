package windycall;

import org.junit.jupiter.api.Test;
import windycall.task.Task;
import windycall.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void fileFormatTest() {
        String description = "finish assignment";
        Task todoTask = new Todo(description, false);
        String expectedFormat = "T |   | finish assignment\n";
        assertEquals(expectedFormat, todoTask.getFileFormat());
    }

    @Test
    public void displayFormatTest(){
        String description = "read book";
        Task todoTask = new Todo(description, true);
        String expectedFormat = "[T][X] read book";
        assertEquals(expectedFormat, todoTask.toString());
    }
}
