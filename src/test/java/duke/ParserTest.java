package duke;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.enums.Views;

public class ParserTest {
    @Test
    public void testParserUnknown() {
        try {
            Parser.parse("ASDF");
        } catch (DukeException e) {
            // Check if duke message is expected
            String expected = Views.UNKNOWN_CMD_ERR_STRING.str();
            Assertions.assertEquals(expected, e.getMessage());
            // Assertion failed, as expected
            return;
        }
        // If the code above didn't throw an DukeException, this line will be reached
        fail("Expected an DukeException to be thrown");
    }

    @Test
    public void testParserExit() throws DukeException {
        Command expected = new ExitCommand();
        Command actual = Parser.parse("bye");
        Assertions.assertEquals(expected.isExit(), actual.isExit());
    }

    @Test
    public void testParserNotExit() throws DukeException {
        Command expected = new ClearCommand();
        Command actual = Parser.parse("clear");
        Assertions.assertEquals(expected.isExit(), actual.isExit());
    }

    @Test
    public void testHandleTaskFail() {
        try {
            Parser.handleTaskInput("ASDF");
        } catch (DukeException e) {
            // Check if duke message is expected
            String expected = Views.UNKNOWN_CMD_ERR_STRING.str();
            Assertions.assertEquals(expected, e.getMessage());
            // Assertion failed, as expected
            return;
        }
        // If the code above didn't throw an DukeException, this line will be reached
        fail("Expected an DukeException to be thrown");
    }

    @Test
    public void testHandleTask() throws DukeException {
        String[] todoExpected = { " title", null, null };
        String[] todo = Parser.handleTaskInput("todo title");
        Assertions.assertArrayEquals(todoExpected, todo);

        String[] deadlineExpected = { " title ", "/by tmr", null };
        String[] deadline = Parser.handleTaskInput("deadline title /by tmr");
        Assertions.assertArrayEquals(deadlineExpected, deadline);

        String[] eventExpected = { " test ", "/from today ", "/to tmr" };
        String[] event = Parser.handleTaskInput("event test /from today /to tmr");
        Assertions.assertArrayEquals(eventExpected, event);
    }

    @Test
    public void testSubtractIntFail() {
        try {
            Parser.parse("mark 0");
        } catch (DukeException e) {
            // Check if assert message is expected
            String expected = Views.OUT_RANGE_ERR_STRING.str();
            Assertions.assertEquals(expected, e.getMessage());
            // Assertion failed, as expected
            return;
        }
        // If the code above didn't throw an DukeException, this line will be reached
        fail("Expected an DukeException to be thrown");
    }

    @Test
    public void testGetNumbersFail() {
        try {
            Parser.parse("mark");
        } catch (DukeException e) {
            // Check if assert message is expected
            String expected = Views.NO_INT_ERR_STRING.str();
            Assertions.assertEquals(expected, e.getMessage());
            // Assertion failed, as expected
            return;
        }
        // If the code above didn't throw an DukeException, this line will be reached
        fail("Expected an DukeException to be thrown");
    }
}
