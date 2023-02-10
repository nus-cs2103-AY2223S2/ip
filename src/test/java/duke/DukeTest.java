package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.constant.DialogType;
import duke.constant.Message;
import duke.exception.DukeException;


public class DukeTest {

    private Duke duke;

    @BeforeEach
    void setUp() {
        duke = new Duke();
    }

    @Test
    void testGetResponse() {
        duke.getResponse("hi", (ty, reply) -> {
            assertEquals(ty.toString(), DialogType.ERROR.toString());
            assertEquals(reply, new DukeException(Message.EXCEPTION_NOSUCH_COMMAND).getMessage().toString());
        });
    }

    @Test
    void testClose() {
        duke.close();
        duke.getResponse("todo 1", (ty, reply) -> {
            assertEquals(ty.toString(), DialogType.ERROR.toString());
            assertEquals(reply, new DukeException(Message.EXCEPTION_DB_CLOSED).getMessage().toString());
        });
    }
}
