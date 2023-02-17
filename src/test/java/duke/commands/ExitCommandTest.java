package duke.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExitCommandTest {
    @Test
    public void test1() {
        Command c = new ExitCommand();
        assertTrue(c.isExit());
    }
}
