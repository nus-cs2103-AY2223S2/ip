package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

public class FindCommandTest {
    @Test
    public void checkKeywordCommand() {

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Storage storage = new Storage();
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        FindCommand fc = new FindCommand("hello");
        fc.execute(tasks, ui, storage);
        String actual = outputStreamCaptor.toString();

        ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(expectedStream));

        ui.printResponse("You have no tasks containing the following search term:\n    hello");
        String expected = expectedStream.toString();

        assertEquals(expected, actual);
    }
}
