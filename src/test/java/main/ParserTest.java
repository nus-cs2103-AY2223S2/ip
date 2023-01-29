package test.java.main;

import command.DeadlineCommand;
import command.EventsCommand;
import command.TodoCommand;
import command.DeleteCommand;
import command.MarkCommand;
import command.Bye;
import command.ListCommand;
import exception.DukeException;

import main.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    void parserTest() throws DukeException  {
        Parser parser = new Parser();
        assertTrue(parser.parse("bye") instanceof Bye);
        assertTrue(parser.parse("list") instanceof ListCommand);
        assertTrue(parser.parse("done 2") instanceof MarkCommand);
        assertTrue(parser.parse("delete 2") instanceof DeleteCommand);

        assertTrue(parser.parse("todo todo") instanceof TodoCommand);
        assertTrue(parser.parse("deadline deadline /by 25/07/2015 1500") instanceof DeadlineCommand);
        assertTrue(parser.parse("event event /at 26/07/2014 1600") instanceof EventsCommand);
    }
}