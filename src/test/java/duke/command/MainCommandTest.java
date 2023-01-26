package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainCommandTest extends CommandTest {
    @Test
    public void testErrorCommand() {
        Command error = new ErrorCommand("This is an error");
        error.execute(taskList, ui);

        String expectedUi =
                "      ____________________________________________________________\n" +
                "Rick: This is an error\n" +
                "      ____________________________________________________________\n\n";
        String actualUi = outContent.toString();
        outContent.reset();
        assertEquals(expectedUi, actualUi);
    }

    @Test
    public void testExitCommand() {
        Command exit = new ExitCommand();
        assertTrue(exit.isExit());
        exit.execute(taskList, ui);

        String expectedUi =
                "      ____________________________________________________________\n" +
                "Rick: It was okay serving you. Might/might not see you again.\n" +
                "      Exiting...\n" +
                "      ____________________________________________________________\n\n";
        String actualUi = outContent.toString();
        outContent.reset();
        assertEquals(expectedUi, actualUi);

    }
}
