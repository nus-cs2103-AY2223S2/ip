package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class ParserTest {
    @Test
    public void testResponseBye(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Storage storage = new Storage("duke.txt");
        String byeMessage = "bye";
        Parser.parse(ui, tasks, storage, byeMessage);
        assertEquals(Ui.TOP_DIVIDER + "Goodbye! Have a nice day ahead.\n" +  Ui.BOTTOM_DIVIDER + "\n",
                outContent.toString());
        System.setOut(System.out);

    }

    @Test
    public void testErrorMessage(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Storage storage = new Storage("duke.txt");
        String invalidMessage= "invalid command";
        Parser.parse(ui, tasks, storage, invalidMessage);
        assertEquals(Ui.TOP_DIVIDER + "Oops! I don't know what this means." +  Ui.BOTTOM_DIVIDER + "\n",
                outContent.toString());
        System.setOut(System.out);
    }
}
