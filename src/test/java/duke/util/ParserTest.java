package duke.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testBye(){
        Command bye = Parser.parse("bye");
        assertEquals(Command.CommandType.EXIT, bye.getCommandType());
        assertEquals(null, bye.getParam());
    }

    @Test
    public void testAddTodo(){
        Command addTodo = Parser.parse("todo something later");
        String expected = "todo something later";
        assertEquals(Command.CommandType.ADD_TASK, addTodo.getCommandType());
        assertEquals(expected, addTodo.getParam());
    }

    @Test
    public void testInvalid(){
        Command invalid = Parser.parse("random input");
        assertEquals(Command.CommandType.INVALID_COMMAND, invalid.getCommandType());
        assertEquals(null, invalid.getParam());
    }
}