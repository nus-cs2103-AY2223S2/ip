package duke.command;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void isExitTest() {
        assertEquals(true, new EndCommand().isExit());
        assertEquals(false, new DeadlineCommand("").isExit());
        assertEquals(false, new DeleteCommand(1).isExit());
        assertEquals(false, new EventCommand("").isExit());
        assertEquals(false, new GuideCommand().isExit());
        assertEquals(false, new MarkCommand(1).isExit());
        assertEquals(false, new PrintListCommand().isExit());
        assertEquals(false, new ToDoCommand("").isExit());
        assertEquals(false, new UnknownCommand().isExit());
        assertEquals(false, new UnmarkCommand(1).isExit());
    }
}
