package command;

import duke.ui.Ui;
import duke.commands.DeadlineCommand;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
    TaskList tasks;
    Ui ui;
    Storage storage;

    @BeforeEach
    public void init() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage("./data/");
    }

    @Test
    public void shouldAddDeadline() {
        DeadlineCommand demoDeadlineCommand = new DeadlineCommand("Demo /by 2002-02-12 19:00");
        demoDeadlineCommand.execute(this.tasks, this.ui, this.storage);
        Task actual = this.tasks.showTask(0);
        Deadline expected = new Deadline("Demo","2002-02-12 19:00");
        assertEquals(expected, actual);
    }
}
