import Task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testCreateToDo() {
        String testcase = "read book";
        ToDo a = new ToDo(testcase);
        ToDo b = ToDo.createToDo("todo " + testcase);
        assertEquals(a.toString(),b.toString());
    }

    @Test
    public void testToString() {
        String testcase = "read book";
        ToDo a = new ToDo(testcase);
        assertEquals(a.toString(), "[T][] read book");
    }
}
