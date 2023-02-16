package peppa;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import peppa.commands.DeadlineCommand;

public class DeadlineCommandTest {
    @Test
    public void getParameterValue_noDescription_exceptionThrown() {
        Exception exception = assertThrows(PeppaException.class, () -> {
            String command = "deadline /by 02/02/2023 2359";
            int paramIndex = DeadlineCommand.getParameterIndex(command);
            DeadlineCommand.getParameterValue(command, DeadlineCommand.DESC_INDEX, paramIndex - 1);
        });
        assertTrue(exception.getMessage().contains("could not process the request"));
    }
}
