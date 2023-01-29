import tasks.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void testToFileString(){
        assertEquals("T | 0 | task", new Todo("task").toFileString());
    }

    @Test
    public void testToString(){
        assertEquals("[T][ ] task", new Todo("task").toString());
    }
}
