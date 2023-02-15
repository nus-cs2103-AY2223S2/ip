import duke.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.TaskList;

public class TaskListTest {
    @Test
    public void testTaskListLoad() {
        Storage storage = new Storage();
        TaskList tasks = new TaskList(storage.load());
        Assertions.assertNotNull(TaskList.getUserTasks());
    }
}
