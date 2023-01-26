package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandsTest {

    @Test
    public void executeToDoCommandTest() {
        Task testTask = new ToDo("Duke");
        Storage storage = new Storage();
        TaskList taskList = storage.readSavedFile();
        TextUi TextUi = new TextUi();
        Integer indexOfNewTask = 0;
        try {
            Commands.executeToDoCommand("Duke", TextUi, taskList, storage);
            indexOfNewTask = taskList.getArraySize() - 1;
        } catch (DukeException e) {
            TextUi.getCustomMessage(e.getMessage());
        }
        assertEquals(taskList.getTask(indexOfNewTask).toString(), new ToDo("Duke").toString());
    }
    @Test
    public void executeMarkCommandTest() throws DukeException {
        Task testTask = new ToDo("Duke");
        Storage storage = new Storage();
        TaskList taskList = storage.readSavedFile();
        TextUi TextUi = new TextUi();
        Commands.executeToDoCommand("Duke", TextUi, taskList, storage);
        Integer indexOfNewTask;
        try {
            indexOfNewTask = taskList.getArraySize();
            Commands.executeMarkCommand(indexOfNewTask.toString(), TextUi, taskList, storage);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
        assertTrue(taskList.getTask(indexOfNewTask -1).isDone());
    }
}