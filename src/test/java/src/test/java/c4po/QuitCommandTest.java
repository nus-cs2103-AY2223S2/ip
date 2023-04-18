package src.test.java.c4po;

import org.junit.jupiter.api.Test;
import src.main.c4po.Command;
import src.main.c4po.QuitCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuitCommandTest {

    @Test
    public void exitQueTest() {
        Command quitCommand = new QuitCommand();
        assertTrue(quitCommand.isExit());
    }

}
