package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    TaskList taskList = new TaskList();

    @Test
    public void addTasksTest() {
        Task todoTask = new Todo("read book");
        Deadline deadlineTask = new Deadline("homework", "2023-10-10 2359");
        Event eventTask = new Event("birthday party", "2023-03-11 1600", "2023-03-11 2100");
        taskList = taskList.add(todoTask).add(deadlineTask).add(eventTask);
        String expectedTaskList = "1. [T][ ] read book\n"
                + "2. [D][ ] homework (by: Oct 10 2023 23:59)\n"
                + "3. [E][ ] birthday party (from: Mar 11 2023 16:00 to: Mar 11 2023 21:00)";
        assertEquals(expectedTaskList, taskList.toString());
    }

    @Test
    public void deleteTasksTest() {
        Task todoTask = new Todo("read book");
        Deadline deadlineTask = new Deadline("homework", "2023-10-10 2359");
        Event eventTask = new Event("birthday party", "2023-03-11 1600", "2023-03-11 2100");
        taskList = taskList.add(todoTask).add(deadlineTask).add(eventTask);
        taskList = taskList.remove(0).remove(0);
        String expectedTaskList = "1. [E][ ] birthday party (from: Mar 11 2023 16:00 to: Mar 11 2023 21:00)";
        assertEquals(expectedTaskList, taskList.toString());
    }

    @Test
    public void markTaskTest() {
        Task todoTask = new Todo("read book");
        todoTask = todoTask.markAsDone();
        taskList = taskList.add(todoTask);
        String expectedTaskList = "1. [T][X] read book";
        assertEquals(expectedTaskList, taskList.toString());
    }
}
