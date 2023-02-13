package commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import membot.Membot;
import membot.commands.ByeCommand;
import membot.commands.Command;
import membot.commands.DeadlineCommand;
import membot.commands.DeleteCommand;
import membot.commands.DoneCommand;
import membot.commands.EventCommand;
import membot.commands.FindCommand;
import membot.commands.HelpCommand;
import membot.commands.ListCommand;
import membot.commands.SortCommand;
import membot.commands.TodoCommand;
import membot.commands.UndoneCommand;
import membot.utils.EmptyInputException;
import membot.utils.InvalidCommandException;
import membot.view.UiPrinter;

public class CommandTest {
    private final Consumer<String> c = System.out::println;
    private final UiPrinter p = new UiPrinter(c);
    private final Membot m = new Membot(p);

    @Test
    public void parseTest() {
        assertThrows(EmptyInputException.class, () -> Command.parse("", p, m));
        assertDoesNotThrow(() -> assertTrue(Command.parse("help", p, m) instanceof HelpCommand));
        assertDoesNotThrow(() -> assertTrue(Command.parse("list", p, m) instanceof ListCommand));
        assertDoesNotThrow(() -> assertTrue(Command.parse("bye", p, m) instanceof ByeCommand));
        assertDoesNotThrow(() -> assertTrue(Command.parse("done", p, m) instanceof DoneCommand));
        assertDoesNotThrow(() -> assertTrue(Command.parse("undone", p, m) instanceof UndoneCommand));
        assertDoesNotThrow(() -> assertTrue(Command.parse("delete", p, m) instanceof DeleteCommand));
        assertDoesNotThrow(() -> assertTrue(Command.parse("find", p, m) instanceof FindCommand));
        assertDoesNotThrow(() -> assertTrue(Command.parse("todo", p, m) instanceof TodoCommand));
        assertDoesNotThrow(() -> assertTrue(Command.parse("deadline", p, m) instanceof DeadlineCommand));
        assertDoesNotThrow(() -> assertTrue(Command.parse("event", p, m) instanceof EventCommand));
        assertDoesNotThrow(() -> assertTrue(Command.parse("sort", p, m) instanceof SortCommand));
        assertThrows(InvalidCommandException.class, () -> Command.parse("sortt", p, m));
        assertThrows(InvalidCommandException.class, () -> Command.parse("somerandomcommand", p, m));
    }
}
