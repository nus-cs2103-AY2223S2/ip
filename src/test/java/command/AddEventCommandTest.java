package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chad.command.AddEventCommand;
import chad.command.Command;
import chad.storage.TaskList;

public class AddEventCommandTest {

    private TaskList tasks;

    @BeforeEach
    public void init() {
        this.tasks = new TaskList();
    }

    @Test
    public void eventTest() {
        Command addEventTest = new AddEventCommand("event testing /from 2023-01-27 2311 /to 2023-01-29 2311");
        addEventTest.execute(tasks);
        String expected = "[E][ ] testing (from: Jan 27 2023, 11:11PM to: Jan 29 2023, 11:11PM)";
        assertEquals(expected, tasks.getTask(0).toString());
    }
}
