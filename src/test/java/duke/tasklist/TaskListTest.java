package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskStub;

public class TaskListTest {
    @Test
    @DisplayName("Task list append test")
    public void appendTest() {
        TaskList taskList = new TaskList();
        Task t = new TaskStub();
        taskList.append(t);
        assertEquals(1, taskList.getLength());
    }

}
