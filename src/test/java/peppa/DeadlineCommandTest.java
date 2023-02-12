package peppa;

import org.junit.jupiter.api.Test;
import peppa.commands.DeadlineCommand;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineCommandTest {
    @Test
    public void getParameterValue_noDescription_exceptionThrown() {
        Exception exception = assertThrows(PeppaException.class, () -> {
            String command = "deadline /by 2359";
            int paramIndex = DeadlineCommand.getParameterIndex(command);
            DeadlineCommand.getParameterValue(command, DeadlineCommand.DESC_INDEX, paramIndex - 1);
        });
        assertTrue(exception.getMessage().contains("could not process the request"));
    }
}