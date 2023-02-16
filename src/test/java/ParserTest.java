import duke.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parserTest1() throws IOException, DukeException, ParseException {
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks");
        Tasklist tasklist = new Tasklist();
        Parser parser = new Parser();
        parser.parse("todo read", tasklist, ui, storage);
        parser.parse("mark 1", tasklist, ui, storage);
        assertEquals(true, tasklist.getList().get(0).isDoneStatus());
    }

    @Test
    public void parserTest2() throws IOException, DukeException, ParseException {
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks");
        Tasklist tasklist = new Tasklist();
        Parser parser = new Parser();
        assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-( Type /help for user guide.", parser.parse("bird", tasklist, ui, storage));
    }
}