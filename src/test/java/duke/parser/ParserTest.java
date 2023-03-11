package duke.parser;

import duke.enums.Commands;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parseInput_validTodoString_success() throws DukeException {
        assertEquals(Commands.TODO, new Parser().parseInput("todo todo1"));
    }

    @Test
    public void parseInput_todoNoDescription_exceptionThrown() {
        try {
            new Parser().parseInput("todo ");
            fail();
        } catch (DukeException e) {
            assertEquals(
                    "Invalid description provided. " +
                            "The description of a task cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void parseInput_deadlineNoDate_exceptionThrown() {
        try {
            new Parser().parseInput("deadline deadline /by ");
            fail();
        } catch (DukeException e) {
            assertEquals("Please provide a valid deadline.", e.getMessage());
        }
    }

    @Test
    public void parseInput_deadlineInvalidFormat_exceptionThrown() {
        try {
            new Parser().parseInput("deadline deadline /by2023-12-12");
            fail();
        } catch (DukeException e) {
            assertEquals("Please provide a valid deadline.", e.getMessage());
        }
    }
}
