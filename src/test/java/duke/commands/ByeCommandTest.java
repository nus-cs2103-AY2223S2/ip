package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ByeCommandTest {
    @Test
    public void byeIsExitTest() {
        Command toTest = new ByeCommand();
        assertEquals(true, toTest.isExit());
    }
}
