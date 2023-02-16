package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindCommandTest {
    private final Command command;

    public FindCommandTest() throws DukeException {
        command = new FindCommand("find Hello World");
    }

    @Test
    public void getResponse() {
        // A unit test for AddCommand#getResponse
        // test setup
        String response = "Command has not yet been executed";

        // automatically verify the response
        assertEquals(command.getResponse(), response);
    }

    @Test
    public void setResponse() {
        // A unit test for AddCommand#setResponse
        // test setup
        String response = "Hello World";
        command.setResponse(response);

        // automatically verify the response
        assertEquals(command.getResponse(), response);
    }

    @Test
    public void isExit() {
        // A unit test for AddCommand#isExit

        // automatically verify the response
        assertFalse(command.isExit());
    }

    @Test
    public void exit() {
        // A unit test for AddCommand#exit
        // test setup
        command.exit();

        // automatically verify the response
        assertTrue(command.isExit());
    }

    @Test
    public void execute() throws DukeException {
        // A unit test for FindCommand#execute
        // test 1 setup
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/test.txt");
        command.execute(tasks, ui, storage);

        // automatically verify the response
        assertEquals(command.getResponse(),
                "There is no such task in your list.");

        // test 2 setup
        tasks.add(new Task("Hello World"));
        command.execute(tasks, ui, storage);

        // automatically verify the response
        assertEquals(command.getResponse(),
                "Here are the matching tasks in your list:\n"
                        + "1.[ ] Hello World");
    }
}
