package spongebob;

import org.junit.jupiter.api.Test;
import spongebob.command.AddCommand;
import spongebob.exception.SpongebobEmptyArgumentException;
import spongebob.exception.SpongebobEventOverlapException;
import spongebob.exception.SpongebobInvalidArgumentException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpongebobTest {
    @Test
    public void getResponse() throws SpongebobEmptyArgumentException,
            SpongebobInvalidArgumentException, SpongebobEventOverlapException {
        assertEquals(AddCommand.class, ParserStub.parse("todo do ip").getClass());
    }
}
