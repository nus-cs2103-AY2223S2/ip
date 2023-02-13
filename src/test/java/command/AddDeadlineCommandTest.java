package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chad.command.AddDeadlineCommand;
import chad.command.Command;
import chad.storage.TaskList;

public class AddDeadlineCommandTest {

    private TaskList tasks;

    @BeforeEach
    public void init() {
        this.tasks = new TaskList();
    }

    @Test
    public void deadlineTest() {
        Command addDeadlineTest = new AddDeadlineCommand("deadline test /by 2023-01-29 2311");
        addDeadlineTest.execute(tasks);
        String expected = "[D][ ] test (by: Jan 29 2023, 11:11PM)";
        assertEquals(expected, tasks.getTask(0).toString());
    }

}
