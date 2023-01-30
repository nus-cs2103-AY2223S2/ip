package alfred.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AlfredExceptionTest {

    @Test
    public void testStringConversion() {
        String test = "OOPS!!! This is a test error!";
        assertEquals(test, new AlfredException("This is a test error!").toString());
    }
}
