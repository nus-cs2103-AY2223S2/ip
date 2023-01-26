package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.Tasks.Task;
import seedu.duke.Tasks.Todo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTask_success() {
        TaskList taskList = new TaskList();
        ArrayList<Task> taskListArr = new ArrayList<>();
        Task addedTask = new Todo("eat book");
        taskList = taskList.addTask(addedTask);
        taskListArr.add(addedTask);
        TaskList updatedTaskList = new TaskList(taskListArr);
        assertEquals(updatedTaskList, taskList);
    }

    @Test
    public void deleteIndexZeroTask_success() {
        // add task first
        TaskList taskList = new TaskList();
        ArrayList<Task> taskListArr = new ArrayList<>();
        Task addedTask = new Todo("return book");
        taskList = taskList.addTask(addedTask);
        taskListArr.add(addedTask);

        // then delete index 0
        int index = 0;
        taskList = taskList.deleteTask(index);
        taskListArr.remove(index);
        TaskList updatedTaskList = new TaskList(taskListArr);
        assertEquals(updatedTaskList, taskList);
    }
}
