package command;

import duke.ui.Ui;
import duke.commands.EventCommand;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.tasks.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventCommandTest {
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
    public void shouldAddEvent() {
        EventCommand demoEventCommand = new EventCommand("Demo /from 2002-02-12 19:00 /to 2012-02-12 21:00");
        demoEventCommand.execute(this.tasks, this.ui, this.storage);
        Task actual = this.tasks.showTask(0);
        Event expected = new Event("Demo","2002-02-12 19:00", "2012-02-12 21:00");
        assertEquals(expected, actual);
    }
}

