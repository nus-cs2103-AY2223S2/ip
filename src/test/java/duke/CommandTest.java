package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {

    @Test
    public void execute_markCommand() {
        Command testCommand = new MarkCommand("2");

        ArrayList<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(new Todo("sample task 1"));
        taskArrayList.add(new Todo("sample task 2"));
        TaskList tasks = new TaskList(taskArrayList);
        Ui ui = new Ui();
        Storage storage = new Storage(System.getProperty("user.dir") + "/tasklist.txt");

        try {
            testCommand.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } finally {
            assertTrue(tasks.getTaskList().get(2-1).isDone());
        }
    }

    @Test
    public void test_isExit_byeCommand() {
        Command testCommand = new ByeCommand("");
        assertFalse(testCommand.isExit());
    }

    @Test
    public void test_isNotExit_deadlineCommand() {
        Command testCommand = new DeadlineCommand("");
        assertTrue(testCommand.isExit());
    }

}
