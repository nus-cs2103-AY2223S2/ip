package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(Task.makeTask("event competition /from 22/10/2022 0800 /to 22/10/2022 1700"));
        String expected = "1. [E][ ] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)";
        assertEquals(expected, taskList.toString());

        taskList.addTask(Task.makeTask("deadline 2022 /by 31/12/2022 2359"));
        expected += "\n2. [D][ ] 2022 (by: Dec 31 2022 2359)";
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testMarkTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(Task.makeTask("event competition /from 22/10/2022 0800 /to 22/10/2022 1700"));
        String expected = "1. [E][ ] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)\n";
        taskList.addTask(Task.makeTask("deadline 2022 /by 31/12/2022 2359"));
        expected += "2. [D][ ] 2022 (by: Dec 31 2022 2359)";

        taskList.markTask(0);
        taskList.markTask(1);
        expected = expected.replace("[ ]", "[X]");
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(Task.makeTask("event competition /from 22/10/2022 0800 /to 22/10/2022 1700"));
        String expected = "1. [E][X] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)";
        taskList.addTask(Task.makeTask("deadline 2022 /by 31/12/2022 2359"));

        taskList.markTask(0);
        taskList.markTask(1);

        taskList.deleteTask(1);
        assertEquals(expected, taskList.toString());
    }

    @Test
    public void testUnMarkTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(Task.makeTask("event competition /from 22/10/2022 0800 /to 22/10/2022 1700"));
        String expected = "1. [E][ ] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)";
        taskList.addTask(Task.makeTask("deadline 2022 /by 31/12/2022 2359"));

        taskList.markTask(0);
        taskList.markTask(1);

        taskList.deleteTask(1);
        taskList.unMarkTask(0);
        assertEquals(expected, taskList.toString());
    }
}
