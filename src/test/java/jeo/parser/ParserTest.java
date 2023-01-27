package jeo.parser;

import jeo.exception.JeoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testToDoWithoutTaskDescriptionError() {
        try {
            Parser.parseString("todo");
        } catch (JeoException e) {
            assertEquals("[Error] Please enter a task description.", e.getMessage());
        }
    }

    @Test
    public void testMarkTaskUsingStringAndNotIntegerError() {
        try {
            Parser.parseString("mark two");
        } catch (JeoException e) {
            assertEquals("[Error] Task number needs to be an integer value.", e.getMessage());
        }
    }
}
