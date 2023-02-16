package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class ExitCommandTest {
    @Test
    public void test1() {
        Command c = new ExitCommand();
        assertFalse(c.isExit());
    }
}
