import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import duke.task.ToDo;
public class TodoTest {

    @Test
    public void testTodo() {
        assertEquals("todo borrow book  @ 0", new ToDo("borrow book",
                "todo borrow book").toString());
    }
}
