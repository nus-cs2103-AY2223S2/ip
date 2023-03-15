package jeo.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jeo.exception.JeoException;

public class TodoParserTest {
    @Test
    public void testToDo_withoutTaskDescription_exceptionThrown() {
        try {
            String[] splitInput = new String[]{"todo"};
            new TodoParser().parse(splitInput);
        } catch (JeoException e) {
            assertEquals("[Error] Please enter a task description after todo.\n" +
                    "Command format: todo <description> [/tag <tag>...]", e.getMessage());
        }
    }
}
