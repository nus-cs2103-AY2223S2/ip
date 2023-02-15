import duke.ToDoList;
import duke.tasks.ToDoTask;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoListTest {
    @Test
    public void deleteTest() {
        try {
            ToDoList ls = new ToDoList();
            ToDoTask expected = new ToDoTask("slot 2");
            ls.add(new ToDoTask("slot 1"));
            ls.add(expected);
            ls.add(new ToDoTask("slot 3"));
            assertEquals(expected, ls.delete(2));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getTaskTest() {
        try {
            ToDoList ls = new ToDoList();
            ToDoTask expected = new ToDoTask("slot 1");
            ls.add(expected);
            ls.add(new ToDoTask("slot 2"));
            ls.add(new ToDoTask("slot 3"));
            assertEquals(expected, ls.getTask(1));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
