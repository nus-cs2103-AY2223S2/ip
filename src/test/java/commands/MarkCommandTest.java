package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import exceptions.NoTaskDescriptionException;
import storage.Storage;
import storage.TaskList;
import tasks.Todo;
import ui.Ui;

public class MarkCommandTest {

    @Test
    public void markTest() {

        Storage storage = new Storage();
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        try {
            tasks.add(new Todo("hello"));
            tasks.add(new Todo("number 2"));
            tasks.add(new Todo("3rd one"));
        } catch (NoTaskDescriptionException e) {
            assertNotEquals(e, e);
        }

        // parsing of index is done before MarkCommand is initialised
        MarkCommand mc = new MarkCommand(true, 1);
        String actual = mc.execute(tasks, ui, storage);

        String expected = "This task is marked as done: \n    [T] [X] number 2";

        assertEquals(expected, actual);
    }
}
