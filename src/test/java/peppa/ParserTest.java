package peppa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import peppa.commands.Command;
import peppa.commands.TodoCommand;

public class ParserTest {
    @Test
    public void parseCommand_addTodo_returnTodoCommand() {
        Command cmd = Parser.parseCommand("todo clean room");
        assertEquals(cmd, new TodoCommand("clean room"));
    }
}
