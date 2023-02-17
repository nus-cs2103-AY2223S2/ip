package commands;

import org.junit.jupiter.api.Test;

import exceptions.NoTaskDescriptionException;
import storage.Storage;
import storage.TaskList;
import tasks.Todo;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class MarkCommandTest {

    private final PrintStream standardOut = System.out;

    @Test
    public void markTest() {

        Storage storage = new Storage();
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
    
        try {
            tasks.add(new Todo("hello"));
            tasks.add(new Todo("number 2"));
            tasks.add(new Todo("3rd one"));
        } catch (NoTaskDescriptionException ignore) { }


        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

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