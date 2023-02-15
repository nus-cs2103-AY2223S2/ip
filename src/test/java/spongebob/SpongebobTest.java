package spongebob;

import spongebob.exception.SpongebobEmptyArgumentException;
import spongebob.exception.SpongebobEventOverlapException;
import spongebob.exception.SpongebobInvalidArgumentException;
import spongebob.task.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpongebobTest {
    @Test
    public void getResponse() throws SpongebobEmptyArgumentException,
            SpongebobInvalidArgumentException, SpongebobEventOverlapException {
        assertEquals(ToDos.class, ParserStub.parse("todo do ip").getClass());
    }
}
