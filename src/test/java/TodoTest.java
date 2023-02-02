import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import duke.ToDo;
public class TodoTest {

    @Test
    public void testTodo() {
        assertEquals("[T][ ] borrow book", new ToDo("todo borrow book").printTask());
    }
}
