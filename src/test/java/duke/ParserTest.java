package duke;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.ExitCommand;

public class ParserTest {
    @Test
    public void testParserUnknown() {
        try {
            Parser.parse("ASDF");
        } catch (DukeException e) {
            // Check if duke message is expected
            String expected = "Hey, ☹ I'm sorry, but I don't know what that means :-(";
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
            Parser.handleTask("ASDF");
        } catch (DukeException e) {
            // Check if duke message is expected
            String expected = "Hey, ☹ I'm sorry, but I don't know what that means :-(";
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
        String[] todo = Parser.handleTask("todo title");
        Assertions.assertArrayEquals(todoExpected, todo);

        String[] deadlineExpected = { " title ", "/by tmr", null };
        String[] deadline = Parser.handleTask("deadline title /by tmr");
        Assertions.assertArrayEquals(deadlineExpected, deadline);

        String[] eventExpected = { " test ", "/from today ", "/to tmr" };
        String[] event = Parser.handleTask("event test /from today /to tmr");
        Assertions.assertArrayEquals(eventExpected, event);
    }

    @Test
    public void testSubtractIntFail() throws DukeException {
        try {
            Parser.parse("mark 0");
        } catch (AssertionError e) {
            // Check if assert message is expected
            String expected = "Hey, the number you've entered is not valid";
            Assertions.assertEquals(expected, e.getMessage());
            // Assertion failed, as expected
            return;
        }
        // If the code above didn't throw an AssertionError, this line will be reached
        fail("Expected an AssertionError to be thrown");
    }

    @Test
    public void testGetNumbersFail() {
        try {
            Parser.parse("mark");
        } catch (DukeException e) {
            // Check if assert message is expected
            String expected = "Hey, you did not enter any numbers";
            Assertions.assertEquals(expected, e.getMessage());
            // Assertion failed, as expected
            return;
        }
        // If the code above didn't throw an DukeException, this line will be reached
        fail("Expected an DukeException to be thrown");
    }
}
