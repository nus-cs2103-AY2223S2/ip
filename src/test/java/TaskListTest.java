import duke.helper.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTest() {
        TaskList t = new TaskList();
        t.addTask(new ToDo("Hello"));
        assertEquals(t.getTasks().size(), 1);
    }

    @Test
    public void deleteTest() {
        TaskList t = new TaskList();
        t.addTask(new ToDo("Hello"));
        t.deleteTask(1);
        assertEquals(t.getTasks().size(), 0);
    }
}
