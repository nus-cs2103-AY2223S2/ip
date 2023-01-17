package duke.command;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    private final Command basCommand = new BasicCommand("test", "test help", () -> new String[]{"test"});
    private final Command argCommand = new ArgCommand("test2", "test2 help", new String[]{"\\s"}, (params) -> new String[]{"test"});

    @Test
    void testBasCommand() {
        assertEquals("test", basCommand.getName());
        assertEquals("test help", basCommand.getHelpStr());
        assertFalse(basCommand.hasParams());
        assertArrayEquals(new String[]{}, basCommand.getParams());
        assertArrayEquals(new String[]{"test"}, basCommand.execute(new String[]{}));
    }

    @Test
    void testArgCommand() {
        assertEquals("test2", argCommand.getName());
        assertEquals("test2 help", argCommand.getHelpStr());
        assertTrue(argCommand.hasParams());
        assertArrayEquals(new String[]{"\\s"}, argCommand.getParams());
        assertArrayEquals(new String[]{"test"}, argCommand.execute(new String[]{}));
    }
}