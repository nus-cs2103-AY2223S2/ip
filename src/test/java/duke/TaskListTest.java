package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class TaskListTest {

    @Test
    public void create_listInput_newList() {
        ArrayList<Task> testList = new ArrayList<>(Arrays.asList(
                new ToDo("Task One", null),
                new ToDo("Task (2)", null)
        ));
        ArrayList<Task> expectedList = new ArrayList<>(Arrays.asList(
                new ToDo("Task One", null),
                new ToDo("Task (2)", null)
        ));

        TaskList list = new TaskList(testList);
        assertEquals(list.getList(), expectedList);
    }

    @Test
    public void addTask_normalInput_success() {
        ArrayList<Task> expectedList = new ArrayList<>(Arrays.asList(
                new ToDo("Task 1", null),
                new Deadline("Task 2", LocalDate.parse("2000-01-01")),
                new Event("Task 3", "now", "later")
        ));

        TaskList list = new TaskList();
        list.addTask(new ToDo("Task 1", null));
        list.addTask(new Deadline("Task 2", LocalDate.parse("2000-01-01")));
        list.addTask(new Event("Task 3", "now", "later"));
        assertEquals(list, new TaskList(expectedList));
    }
}
