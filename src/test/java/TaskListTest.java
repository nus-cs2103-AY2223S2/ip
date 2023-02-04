import Baymax.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    TaskList task = new TaskList();

    @Test
    public void testNullList() {
        assertEquals(null, task.getTaskList());
    }
}
