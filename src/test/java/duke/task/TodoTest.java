package duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Todo task = new Todo("assignment");
    @Test
    public void todoTest() {
        assertEquals("[T][ ] assignment",task.toString());
    }

    @Test
    public void markTodoTask() {
        task.markAsDone();
        assertEquals("[T][X] assignment",task.toString());
    }
}
