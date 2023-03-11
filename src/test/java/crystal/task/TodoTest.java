package crystal.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Represents the TodoTest class.
 *
 */
public class TodoTest {

    /**
     * test if the Todo class is working as intended
     *
     */
    @Test
    public void testTodo(){
        Todo todo = new Todo("borrow book");
        assertEquals("[T][ ] borrow book", todo.toString());
    }

}
