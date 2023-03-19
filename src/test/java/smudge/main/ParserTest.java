package smudge.main;

import smudge.command.AddCommand;
import smudge.command.DeleteCommand;
import smudge.command.ExitCommand;
import smudge.command.ListCommand;
import smudge.command.MarkCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    void parserTest() throws SmudgeException {
        assertTrue(Parser.parseCommand("bye") instanceof ExitCommand);
        assertTrue(Parser.parseCommand("list") instanceof ListCommand);
        assertTrue(Parser.parseCommand("mark 2") instanceof MarkCommand);
        assertTrue(Parser.parseCommand("delete 2") instanceof DeleteCommand);

        assertTrue(Parser.parseCommand("todo todo") instanceof AddCommand);
        assertTrue(Parser.parseCommand("deadline deadline /by 25/07/2023 1500") instanceof AddCommand);
        assertTrue(Parser.parseCommand("event event /from 26/01/2023 1600 /to 02/02/2023 1200") instanceof AddCommand);
    }
}