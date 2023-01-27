package jeo.parser;

import jeo.exception.JeoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testToDo_withoutTaskDescription_exceptionThrown() {
        try {
            Parser.parseString("todo");
        } catch (JeoException e) {
            assertEquals("[Error] Please enter a task description.", e.getMessage());
        }
    }

    @Test
    public void testMarkTask_usingStringAndNotInteger_exceptionThrown() {
        try {
            Parser.parseString("mark two");
        } catch (JeoException e) {
            assertEquals("[Error] Task number needs to be an integer value.", e.getMessage());
        }
    }
}
