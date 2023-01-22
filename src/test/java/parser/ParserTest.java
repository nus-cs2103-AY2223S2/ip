package parser;

import dukeException.CommandException.InputFormatException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void givenInt_whenParseInt_thenReturnInt() {
        assertEquals(5, Parser.parseInt("5", "ParserTest"));
        assertEquals(-5, Parser.parseInt("-5", "ParserTest"));
    }

    @Test
    public void givenNonInt_whenParseInt_thenThrowError() {
        Exception exception = assertThrows(InputFormatException.class, () -> {
            Parser.parseInt("a", "ParserTest");
        });

        String expectedMessage = "Haiya this not number. FAILURE.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
