package task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getTest() {
        Task task1 = new TodoTask("Do work");
        Task[] taskArray = {task1};
        TaskList inputTaskList = new TaskList(taskArray);
        assertEquals(task1, inputTaskList.get(0));
    }

    @Test
    public void secondTest() {
        Task task1 = new TodoTask("Do work");
        Task task2 = new DeadlineTask("Return book", LocalDate.parse("2024-01-01"), true);
        Task[] taskArray = {task1, task2};
        TaskList inputTaskList = new TaskList(taskArray);
        assertEquals(2, inputTaskList.size());
    }
}
