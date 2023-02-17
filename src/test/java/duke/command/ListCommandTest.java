package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommandTest {

    private Ui ui = new Ui();
    private Storage storage = new Storage("../data/duke.txt", ui);
    private TaskList tasklist = new TaskList(storage);

    @Test
    public void listCommandTest() {
        assertEquals("Take a look at ye DREAM goals for 2023 \n",
                new ListCommand().execute(tasklist, storage, ui));
    }
}
