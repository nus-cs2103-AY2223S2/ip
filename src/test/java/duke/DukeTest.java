package duke;

import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeEventOverlapException;
import duke.exception.DukeInvalidArgumentException;
import duke.task.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void getResponse() throws DukeEmptyArgumentException,
            DukeInvalidArgumentException, DukeEventOverlapException {
        assertEquals(ToDos.class, ParserStub.parse("todo do ip").getClass());
    }
}
