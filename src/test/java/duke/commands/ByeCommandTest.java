package duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {
    @Test
    public void byeIsExitTest() {
        Command toTest = new ByeCommand();
        assertEquals(true, toTest.isExit());
    }
}
