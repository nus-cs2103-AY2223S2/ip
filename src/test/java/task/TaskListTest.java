package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TaskListTest {
    @Test
    public void testGetTaskListString() throws DukeException {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("finish homework");
        Task task2 = new Deadline("finish assignment /by 2022-10-22");
        Task task3 = new Event("attend parties /from 2022-10-22 /to 2022-10-23");

        taskList.add(task1);
        assertEquals("1. [T][ ] finish homework", taskList.getTaskListString(true));

        taskList.add(task2);
        assertEquals("1. [T][ ] finish homework\n"
                        + "2. [D][ ] finish assignment (by: Oct 22 2022)",
                taskList.getTaskListString(true));

        taskList.add(task3);
        assertEquals("1. [T][ ] finish homework\n"
                        + "2. [D][ ] finish assignment (by: Oct 22 2022)\n"
                        + "3. [E][ ] attend parties (from: Oct 22 2022 to: Oct 23 2022)",
                taskList.getTaskListString(true));
    }

    @Test
    public void testGetOnlyPartiallyMatchedTaskNames() throws DukeException {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("finish homework");
        Task task2 = new Deadline("finish assignment /by 2022-10-22");
        Task task3 = new Event("attend parties /from 2022-10-22 /to 2022-10-23");

        taskList.add(task1);
        assertEquals(0, taskList.getOnlyPartiallyMatchedTaskNames("  ").size());
        assertEquals(0, taskList.getOnlyPartiallyMatchedTaskNames("finish", " ", "home").size());
        assertEquals(1, taskList.getOnlyPartiallyMatchedTaskNames("finish", "not exist").size());

        taskList.add(task2);
        taskList.add(task3);
        assertEquals(3, taskList.getOnlyPartiallyMatchedTaskNames("finish", "parti").size());
        assertEquals(0, taskList.getOnlyPartiallyMatchedTaskNames(" ").size());
        assertEquals(0, taskList.getOnlyPartiallyMatchedTaskNames("2022").size());
        assertEquals(2, taskList.getOnlyPartiallyMatchedTaskNames("2022", "2023").size());
        assertEquals(2, taskList.getOnlyPartiallyMatchedTaskNames("T", "E").size());
    }

    @Test
    public void testGetFullyMatchedTaskNames() throws DukeException {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("finish homework");
        Task task2 = new Deadline("finish assignment /by 2022-10-22");
        Task task3 = new Event("attend parties /from 2022-10-22 /to 2022-10-23");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        assertEquals(3, taskList.getFullyMatchedTaskNames(" ").size());
        assertEquals(0, taskList.getFullyMatchedTaskNames("finish", "attend", " ").size());
        assertEquals(2, taskList.getFullyMatchedTaskNames("2022").size());
        assertEquals(1, taskList.getFullyMatchedTaskNames("T").size());
    }
}
