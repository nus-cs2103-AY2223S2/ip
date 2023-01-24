package parser;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ui.Ui.LS;

public class ParserTest {
    //following test from https://www.baeldung.com/junit-assert-exception
    @Test
    public void parse_deleteCommandWithoutIndex_exceptionThrown(){
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("delete");
        });

        String expectedMessage = "Invalid format for delete." + LS + "Usage: delete <number>";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
