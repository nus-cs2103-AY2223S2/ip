package twofive.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MissingArgumentExceptionTest {
    @Test
    public void missingArgumentExceptionTest() {
        MissingArgumentException exception = new MissingArgumentException("/by");
        assertEquals("Oops! TwoFive believes that you are missing the argument /by.", exception.getMessage());
    }
}
