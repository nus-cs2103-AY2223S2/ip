package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dukeexception.commandexception.EmptyCommandException;
import dukeexception.commandexception.InputFormatException;

public class ParserTest {
    @Test
    public void parseInt_int_int() {
        assertEquals(5, Parser.parseInt("5", "ParserTest"));
        assertEquals(-5, Parser.parseInt("-5", "ParserTest"));
    }

    @Test
    public void parseInt_nonInt_throwException() {
        Exception exception = assertThrows(InputFormatException.class, () -> {
            Parser.parseInt("a", "ParserTest");
        });

        String expectedMessage = "Haiya this not number. FAILURE.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void parse_empty_throwException() {
        Exception exception = assertThrows(EmptyCommandException.class, () -> {
            Parser.parse("");
        });

        String expectedMessage = "HUH? What you say?";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void handleMissingField_singleWord_throwException() {
        Exception exception = assertThrows(InputFormatException.class, () -> {
            Parser.handleMissingField("eat", "/by", "by", "Event Creation");
        });

        String expectedMessage = "Haiya wrong format.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
