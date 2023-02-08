package seedu.duke;

import duke.ToDo;
import org.junit.jupiter.api.Test;
import util.DukeException;
import util.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;




public class TaskListTest {

    @Test
    public void checkAddTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("return book"));
        assertEquals(taskList.numTasks(), "     Now you have 1 tasks in the list");
    }

    @Test
    public void checkInvalidInput() {
        TaskList taskList = new TaskList();
        assertThrows(DukeException.class, () -> {
            taskList.manageTask("deadline");
        });
    }
}
