package twofive.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MissingArgumentExceptionTest {
    @Test
    public void missingArgumentExceptionTest() {
        MissingArgumentException exception = new MissingArgumentException("/by");
        assertEquals(":( OOPS!!! You are missing the argument /by.", exception.getMessage());
    }
}
