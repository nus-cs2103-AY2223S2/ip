package duke;

import duke.exceptions.DukeException;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.UiController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    public void addTodoToListTest() {
        TaskList taskList = new TaskList();
        try {
            taskList.addToDo(new String[]{"this is not a todo"});
            Assertions.fail();
        } catch (DukeException e) {
            Assertions.fail();
        } catch (AssertionError e) {
            assertTrue(true);
        }

        try {
            taskList.addToDo(new String[]{"todo", "return", "book"});
            assertEquals(1, taskList.size());
        } catch (DukeException e) {
            Assertions.fail();
        }

    }

}
