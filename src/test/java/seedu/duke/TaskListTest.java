package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.Todo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    /**
     * Testing script for adding Tasks to TaskList
     */
    @Test
    public void addTask_success() {
        TaskList taskList = new TaskList();
        ArrayList<Task> tasks = new ArrayList<>();
        Task addedTask = new Todo("eat book");
        taskList = taskList.addTask(addedTask);
        tasks.add(addedTask);
        TaskList updatedTaskList = new TaskList(tasks);
        assertEquals(updatedTaskList, taskList);
    }

    /**
     * Testing script for deleted index zero Tasks from TaskList
     */
    @Test
    public void deleteIndexZeroTask_success() {
        // add task first
        TaskList taskList = new TaskList();
        ArrayList<Task> tasks = new ArrayList<>();
        Task addedTask = new Todo("return book");
        taskList = taskList.addTask(addedTask);
        tasks.add(addedTask);

        // then delete index 0
        int index = 0;
        taskList = taskList.deleteTask(index);
        tasks.remove(index);
        TaskList updatedTaskList = new TaskList(tasks);
        assertEquals(updatedTaskList, taskList);
    }
}
