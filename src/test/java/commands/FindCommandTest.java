package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

public class FindCommandTest {
    @Test
    public void checkKeywordCommand() {

        Storage storage = new Storage();
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        FindCommand fc = new FindCommand("hello");
        String actual = fc.execute(tasks, ui, storage);

        String expected = "You have no tasks containing the following search term:\n    hello";

        assertEquals(expected, actual);
    }
}
