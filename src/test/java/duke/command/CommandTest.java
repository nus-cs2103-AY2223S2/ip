package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.UnknownCommandException;

public class CommandTest {
    @Test
    public void testCommands() {
        try {
            assertEquals(Command.get("list"), Command.LIST);
            assertEquals(Command.get("bye"), Command.BYE);
            assertEquals(Command.get("mark"), Command.MARK);
            assertEquals(Command.get("unmark"), Command.UNMARK);
            assertEquals(Command.get("todo"), Command.TODO);
            assertEquals(Command.get("deadline"), Command.DEADLINE);
            assertEquals(Command.get("event"), Command.EVENT);
            assertEquals(Command.get("delete"), Command.DELETE);
            assertEquals(Command.get("geteventson"), Command.GETEVENTSON);
        } catch (UnknownCommandException e) {
            System.out.println(e);
        }
    }
}
