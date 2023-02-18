package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import exceptions.NoTaskDescriptionException;
import storage.Storage;
import storage.TaskList;
import tasks.Todo;
import ui.Ui;

public class MarkCommandTest {

    private final PrintStream standardOut = System.out;

    @Test
    public void markTest() {

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Storage storage = new Storage();
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        try {
            tasks.add(new Todo("hello"));
            tasks.add(new Todo("number 2"));
            tasks.add(new Todo("3rd one"));
        } catch (NoTaskDescriptionException e) {
            System.out.println(e.getMessage());
        }

        MarkCommand mc = new MarkCommand(true, 2);
        mc.execute(tasks, ui, storage);
        String actual = outputStreamCaptor.toString();


        ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(expectedStream));

        ui.printResponse("This task is marked as done: \n    [T] [X] number 2");
        String expected = expectedStream.toString();

        assertEquals(expected, actual);
    }
}
