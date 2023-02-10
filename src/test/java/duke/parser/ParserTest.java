package duke.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.controllers.CheckoutCommand;
import duke.controllers.Command;
import duke.controllers.GoodbyeCommand;
import duke.controllers.ListCommand;
import duke.controllers.UndoCommand;
import duke.entities.managers.CacheManager;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.stubs.TestCacheManagerStub;
import duke.stubs.TestStorageStub;

class ParserTest {
    private final Storage storage = new TestStorageStub("test.txt");
    private final CacheManager cm = new TestCacheManagerStub(storage);

    ParserTest() throws DukeException {
    }

    @Test
    public void parseInput_IsBye_returnsGoodByeCommand() {
        String input = "bye";
        Command command = Parser.parse(input);
        assertTrue(command instanceof GoodbyeCommand);
    }

    @Test
    public void parseInput_IsList_returnsListCommand() {
        String input = "list";
        Command command = Parser.parse(input);
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parseInput_IsUndo_returnsUndoCommand() {
        String input = "undo";
        Command command = Parser.parse(input);
        assertTrue(command instanceof UndoCommand);
    }

    @Test
    public void parseInput_IsCheckout_returnsCheckoutCommand() {
        String input = "checkout";
        Command command = Parser.parse(input);
        assertTrue(command instanceof CheckoutCommand);
    }

    @Test
    public void parseInput_IsUnknownCommand_throwsDukeException() {
        String input = "hello world";
        Command command = Parser.parse(input);
        try {
            command.execute(() -> cm);
        } catch (DukeException e) {
            assertTrue(true);
        }
    }
}
