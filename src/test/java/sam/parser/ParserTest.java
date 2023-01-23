package sam.parser;

import org.junit.jupiter.api.Test;

import sam.command.ExitCommand;
import sam.command.ListCommand;
import sam.command.MarkCommand;
import sam.task.SamMissingTaskTitleException;
import sam.task.SamMissingTaskValueException;
import sam.SamException;
import sam.command.AddCommand;
import sam.command.DeleteCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
  @Test
  public void parseCommand_validCommands_success() {
    try {
      assertTrue(Parser.parseCommand("bye") instanceof ExitCommand);
      assertTrue(Parser.parseCommand("list") instanceof ListCommand);
      assertTrue(Parser.parseCommand("mark 1") instanceof MarkCommand);
      assertTrue(Parser.parseCommand("unmark 1") instanceof MarkCommand);
      assertTrue(Parser.parseCommand("todo task") instanceof AddCommand);
      assertTrue(Parser.parseCommand("event task /from 1/1/2023 /to 2/1/2023") instanceof AddCommand);
      assertTrue(Parser.parseCommand("deadline task /by 1/1/2023") instanceof AddCommand);
      assertTrue(Parser.parseCommand("delete 1") instanceof DeleteCommand);
    } catch (SamException e) {
      fail();
    }
  }

  @Test
  public void parseCommand_unknownCommands_exceptionThrown() {
    assertThrows(SamUnknownCommandException.class, () -> Parser.parseCommand("hello"));
    assertThrows(SamUnknownCommandException.class, () -> Parser.parseCommand(""));
  }

  @Test
  public void parseTaskArgs_validInputs_success() {
    try {
      assertEquals("task", Parser.parseTaskArgs("task /by 1/1/2023").get("title"));
      assertEquals("1/1/2023", Parser.parseTaskArgs("task /by 1/1/2023").get("by"));
      assertEquals("2/1/2023", Parser.parseTaskArgs("task /from 1/1/2023 /to 2/1/2023").get("to"));
    } catch (SamException e) {
      fail();
    }
  }

  @Test
  public void parseTaskArgs_missingTitle_exceptionThrown() {
    assertThrows(SamMissingTaskTitleException.class, () -> Parser.parseTaskArgs("/from 1/1/2023"));
    assertThrows(SamMissingTaskTitleException.class, () -> Parser.parseTaskArgs(""));
    assertThrows(SamMissingTaskTitleException.class, () -> Parser.parseTaskArgs(" "));
  }

  @Test
  public void parseTaskArgs_missingValue_exceptionThrown() {
    assertThrows(SamMissingTaskValueException.class, () -> Parser.parseTaskArgs("task /to"));
    assertThrows(SamMissingTaskValueException.class, () -> Parser.parseTaskArgs("task /from /to 1/1/2023"));
  }
}