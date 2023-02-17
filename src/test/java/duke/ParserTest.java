package duke;

import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.TodoCommand;;
import duke.commands.ExitCommand;
import duke.commands.DeleteCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeUnknownInputException;
import duke.logic.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setParser() {
        parser = new Parser();
    }

    @Test
    @DisplayName("Test to ensure parser object is initialised.")
    public void createParserTest() {
        assertNotNull(parser, "Parser should be initialised.");
    }

    @Test
    @DisplayName("Test to make sure that DeadlineCommand is returned")
    public void returnDeadlineTest() {
        try {
            assertEquals(parser.parse("deadline").getClass(), DeadlineCommand.class);
        } catch (DukeUnknownInputException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Test to make sure that EventCommand is returned")
    public void returnEventTest() {
        try {
            assertEquals(parser.parse("event").getClass(), EventCommand.class);
        } catch (DukeUnknownInputException e) {
            System.out.println(e);
        }
    }
    @Test
    @DisplayName("Test to make sure that TodoCommand is returned")
    public void returnTodoTest() {
        try {
            assertEquals(parser.parse("todo").getClass(), TodoCommand.class);
        } catch (DukeUnknownInputException e) {
            System.out.println(e);
        }
    }
    @Test
    @DisplayName("Test to make sure that DeleteCommand is returned")
    public void returnDeleteTest() {
        try {
            assertEquals(parser.parse("delete").getClass(), DeleteCommand.class);
        } catch (DukeUnknownInputException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Test to make sure that ExitCommand is returned")
    public void returnExitTest() {
        try {
            assertEquals(parser.parse("bye").getClass(), ExitCommand.class);
        } catch (DukeUnknownInputException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Test to make sure that MarkCommand is returned")
    public void returnMarkTest() {
        try {
            assertEquals(parser.parse("mark").getClass(), MarkCommand.class);
        } catch (DukeUnknownInputException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Test to make sure that UnmarkCommand is returned")
    public void returnUnmarkTest() {
        try {
            assertEquals(parser.parse("unmark").getClass(), UnmarkCommand.class);
        } catch (DukeUnknownInputException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Test to make sure that ListCommand is returned")
    public void returnListTest() {
        try {
            assertEquals(parser.parse("list").getClass(), ListCommand.class);
        } catch (DukeUnknownInputException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Test to ensure exception is thrown upon incorrect input")
    public void throwExceptionTest() {
        Exception e = assertThrows(DukeUnknownInputException.class, () -> parser.parse("blah"));
        String expected = "Whoops! I'm not sure I understand what that means.";
        String actual = e.toString();

        assertTrue(actual.contains(expected));
    }
}
