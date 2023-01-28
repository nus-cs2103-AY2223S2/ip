package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskStub;

/**
 * Test class for the <code>TaskList</code> class.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class TaskListTest {
    /**
     * Append <code>Task</code> to empty <code>TaskList</code>.
     * @result <code>TaskList</code> will have 1 item.
     */
    @Test
    @DisplayName("Task list append test")
    public void appendTest() {
        TaskList taskList = new TaskList();
        Task t = new TaskStub();
        taskList.append(t);
        assertEquals(1, taskList.getLength());
    }

}
