package chattime.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import chattime.exception.ChattimeException;
import chattime.task.Task;

public class ParserTest {
    @Test
    public void markCommand_checkIndexCommandCheckTest() {
        Task testTask = new Task("Test");
        Parser ps = new Parser("mark 1");
        try {
            assertEquals(1, ps.checkIndexCommand());
        } catch (ChattimeException e) {
            fail();
        }
    }
}