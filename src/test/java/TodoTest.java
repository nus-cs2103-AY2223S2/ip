import task.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class TodoTest {

    @Test
    public void testTodoString() {
        Todo todoBuyBooks = new Todo("buy books");
        Assertions.assertEquals("[T][ ] buy books" ,todoBuyBooks.toString());
    }

}
