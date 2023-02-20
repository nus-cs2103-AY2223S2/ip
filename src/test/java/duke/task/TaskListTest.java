package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskListTest {
    @Test
    public void addToDo_normalInput() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        try {
            taskList.addToDo("todo go shopping");
            assertEquals(1, taskList.getTaskList().size());

            Task task = taskList.getTaskList().get(0);
            String expectedResult = "[T][ ] go shopping";
            String actualResult = task.toString();
            assertEquals(expectedResult, actualResult);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void addToDo_emptyInput_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        try {
            taskList.addToDo("todo");
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Please enter task description");
        }
    }
}
