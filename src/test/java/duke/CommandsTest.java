package duke;

import duke.exceptions.NoDescriptionException;
import duke.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandsTest {

    @Test
    public void executeToDoCommandTest() {
        Storage storage = new Storage();
        TaskList taskList = storage.readSavedFile();
        TextUi TextUi = new TextUi();
        int indexOfNewTask = 0;
        try {
            Commands.executeToDoCommand("Duke", TextUi, taskList, storage);
            indexOfNewTask = taskList.getArraySize() - 1;
        } catch (NoDescriptionException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(taskList.getTask(indexOfNewTask).toString(), new ToDo("Duke").toString());
    }
    @Test
    public void executeMarkCommandTest() {
        Storage storage = new Storage();
        TaskList taskList = storage.readSavedFile();
        TextUi TextUi = new TextUi();
        try {
            Commands.executeToDoCommand("Duke", TextUi, taskList, storage);
            int indexOfNewTask = taskList.getArraySize();
            Commands.executeMarkCommand(Integer.toString(indexOfNewTask), taskList, storage);
            assertTrue(taskList.getTask(indexOfNewTask -1).isDone());
        } catch (TaskNotFoundException | NoDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }
}