package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.tasks.Todo;


public class ParserTest {

    @Test
    public void parseTodoTest() {
        String[] testInput = {"todo", "Test Todo"};
        try {
            Todo testTodo = Parser.parseTodo(testInput);
            assertEquals("Test Todo", testTodo.getDescription());
            assertEquals("[T][ ] Test Todo", testTodo.toString());
        } catch (DukeException dukeException) {
            fail();
        }
    }

    @Test
    public void parseTodoTest_exceptionThrown() {
        String[] testInput = {"todo"};
        try {
            Todo testTodo = Parser.parseTodo(testInput);
            fail();
        } catch (DukeException dukeException) {
            assertEquals("Hey!!! The description of a todo cannot be empty.", dukeException.getMessage());
        }
    }

    @Test
    public void validateCommandTest() {
        // Test Case 1: TODO
        Command testTodo = Parser.validateCommand("todo");
        assertEquals(Command.TODO, testTodo);

        // Test Case 2: DEADLINE
        Command testDeadline = Parser.validateCommand("deadline");
        assertEquals(Command.DEADLINE, testDeadline);

        // Test Case 3: EVENT
        Command testEvent = Parser.validateCommand("event");
        assertEquals(Command.EVENT, testEvent);

        // Test Case 4: MARK
        Command testMark = Parser.validateCommand("mark");
        assertEquals(Command.MARK, testMark);

        // Test Case 5: UNMARK
        Command testUnmark = Parser.validateCommand("unmark");
        assertEquals(Command.UNMARK, testUnmark);

        // Test Case 6: DELETE
        Command testDelete = Parser.validateCommand("delete");
        assertEquals(Command.DELETE, testDelete);

        // Test Case 7: LIST
        Command testList = Parser.validateCommand("list");
        assertEquals(Command.LIST, testList);

        // Test Case 8: INVALID
        Command testInvalid = Parser.validateCommand("abcde");
        assertEquals(Command.INVALID, testInvalid);
    }




}
