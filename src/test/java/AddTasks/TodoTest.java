package AddTasks;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void testTodoTask() {
        Todo todo1 = new Todo("read book");
        assertEquals(" [T][ ] read book", todo1.toString());

        Todo todo2 = new Todo("read book");
        todo2.markAsDone();
        assertEquals(" [T][X] read book", todo2.toString());
        todo2.markAsUndone();
        assertEquals(" [T][ ] read book", todo2.toString());
    }
}
