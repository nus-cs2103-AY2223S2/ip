package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddCommandTest {

    private Ui ui = new Ui();
    private Storage storage = new Storage("../data/duke.txt", ui);
    private TaskList tasklist = new TaskList(storage);

    @Test
    public void addCommandTest() {
        assertEquals("Task has been added",
                new AddCommand("TODO", "todo bring books").execute(tasklist, storage, ui));
    }
}
