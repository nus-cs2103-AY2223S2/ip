package parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static duke.ui.Ui.LS;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.parser.Parser;

public class ParserTest {
    //following test from https://www.baeldung.com/junit-assert-exception
    @Test
    public void parse_deleteCommandWithoutIndex_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("delete");
        });

        String expectedMessage = "Invalid format for delete." + LS + "Usage: delete <number>";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
