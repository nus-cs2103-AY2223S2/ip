package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.DukeException;

public class TaskListTest {
    @Test
    public void testGetTaskListString() throws DukeException {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("finish homework");
        Task task2 = new Deadline("finish assignment /by 2022-10-22");
        Task task3 = new Event("attend parties /from 2022-10-22 /to 2022-10-23");

        taskList.add(task1);
        assertEquals(taskList.toString(), "1. [T][ ] finish homework");
        taskList.add(task2);
        assertEquals(taskList.toString(), "1. [T][ ] finish homework\n"
                + "2. [D][ ] finish assignment (by: Oct 22 2022)");

        taskList.add(task3);
        assertEquals(taskList.toString(), "1. [T][ ] finish homework\n"
                + "2. [D][ ] finish assignment\n3. [E][ ] attend parties (from: Oct 22 2022 to: Oct 23 2022)");
    }

    @Test
    public void testGetOnlyPartiallyMatchedTaskNames() throws DukeException {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("finish homework");
        Task task2 = new Deadline("finish assignment /by 2022-10-22");
        Task task3 = new Event("attend parties /from 2022-10-22 /to 2022-10-23");

        taskList.add(task1);
        assertEquals(taskList.getOnlyPartiallyMatchedTaskNames("  ").size(), 0);
        assertEquals(taskList.getOnlyPartiallyMatchedTaskNames("finish", " ", "home").size(), 0);
        assertEquals(taskList.getOnlyPartiallyMatchedTaskNames("finish", "not exist").size(), 1);

        taskList.add(task2);
        taskList.add(task3);
        assertEquals(taskList.getOnlyPartiallyMatchedTaskNames("finish", "parti").size(), 3);
        assertEquals(taskList.getOnlyPartiallyMatchedTaskNames(" ").size(), 0);
        assertEquals(taskList.getOnlyPartiallyMatchedTaskNames("2022").size(), 0);
        assertEquals(taskList.getOnlyPartiallyMatchedTaskNames("2022", "2023").size(), 2);
        assertEquals(taskList.getFullyMatchedTaskNames("T", "E").size(), 2);
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

        assertEquals(taskList.getFullyMatchedTaskNames(" ").size(), 3);
        assertEquals(taskList.getFullyMatchedTaskNames("finish", "attend", " ").size(), 0);
        assertEquals(taskList.getFullyMatchedTaskNames("2022").size(), 2);
        assertEquals(taskList.getFullyMatchedTaskNames("T").size(), 1);
    }
}
