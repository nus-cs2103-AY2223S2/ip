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

public class MarkCommandTest {
    private final String description;
    private final Command command;

    public MarkCommandTest() throws DukeException {
        description = "Hello World";
        Task task = new Task(description);
        command = new MarkCommand("mark 1");
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
        command.setResponse(description);

        // automatically verify the response
        assertEquals(command.getResponse(), description);
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
        // A unit test for MarkCommand#execute
        // test setup
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/test.txt");
        tasks.add(new Task(description));
        command.execute(tasks, ui, storage);

        // automatically verify the response
        assertEquals(command.getResponse(),
                "Nice! I've marked this task as done:\n"
                        + "  [X] Hello World");
    }
}
