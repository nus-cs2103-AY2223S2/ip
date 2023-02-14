package luffy.task;

import luffy.exception.LuffyException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {

    @Test
    public void toFileTest() {
        // test undone
        String expected = "T | 0 | toFileTest\n";
        Todo t = new Todo("toFileTest");
        String actual = t.toFile();
        assertEquals(expected, actual);
        // test done
        expected = "T | 1 | toFileTest\n";
        t.markAsDone();
        actual = t.toFile();
        assertEquals(expected, actual);
    }

    @Test
    public void toTodoFromFileStrTest() {
        try {
            // test undone
            String expected = "[T][ ] toTodoFromFileStrTest";
            Todo t = Todo.toTodoFromFileStr("toTodoFromFileStrTest", "0");
            String actual = t.toString();
            assertEquals(expected, actual);
            // test dont
            expected = "[T][X] toTodoFromFileStrTest";
            t.markAsDone();
            actual = t.toString();
            assertEquals(expected, actual);
        } catch (LuffyException e) {
            fail();
        }
    }

    @Test
    public void toStringTest() {
        // test undone
        String expected = "[T][ ] toStringTest";
        Todo t = new Todo("toStringTest");
        String actual = t.toString();
        assertEquals(expected, actual);
        // test done
        expected = "[T][X] toStringTest";
        t.markAsDone();
        actual = t.toString();
        assertEquals(expected, actual);
    }
}
