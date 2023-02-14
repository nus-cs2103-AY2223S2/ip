import task.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class TodoTest {
    Todo todoBuyBooks = new Todo("buy books");
    @Test
    public void testTodoString(){
        Assertions.assertEquals("[T][ ] buy books" ,todoBuyBooks.toString());
    }
}
