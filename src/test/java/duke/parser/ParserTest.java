package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.exception.DukeException;
import duke.task.DeadlineTask;

class ParserTest {
    @Test
    void parseTest1() {
        String fullCommand = "deadline homework /by 1145-12-19";
        try {
            Command actual = Parser.parse(fullCommand);
            Command expected = new AddTaskCommand(
                    new DeadlineTask("homework", LocalDate.of(1145, 12, 19))
            );
            assertEquals(actual, expected);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void parseTest2() {
        DukeException thrown = assertThrows(DukeException.class, () -> {
                    String fullCommand = "hello!";
                    Command actual = Parser.parse(fullCommand);
                });
        assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", thrown.getMessage());
    }
}
