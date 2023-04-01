package peppa;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import peppa.commands.Command;
import peppa.commands.DeleteCommand;

public class DeleteCommandTest {
    @Test
    public void execute_invalidIndex_exceptionThrown() {
        Command command = new DeleteCommand(-1);
        Exception exception = assertThrows(PeppaException.class, () -> {
            command.execute(null, null, null);
        });
        assertTrue(exception.getMessage().contains("could not find the requested task"));
    }
}
