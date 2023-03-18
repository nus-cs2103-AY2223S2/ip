package jeo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jeo.exception.JeoException;

public class MarkParserTest {
    @Test
    public void testMarkTask_usingStringAndNotInteger_exceptionThrown() {
        try {
            String[] splitInput = new String[]{"mark", "two"};
            new MarkParser().parse(splitInput);
        } catch (JeoException e) {
            assertEquals("[Error] Task number needs to be an integer value.\n"
                    + "Command format: mark <task number>", e.getMessage());
        }
    }
}
