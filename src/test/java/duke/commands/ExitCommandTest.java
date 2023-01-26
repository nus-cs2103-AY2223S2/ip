package duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {
    @Test
    public void test1() {
        Command c = new ExitCommand();
        assertEquals(false, c.isExit());
    }
}
