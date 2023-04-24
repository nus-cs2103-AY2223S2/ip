package twofive.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmptyDescriptionExceptionTest {
    @Test
    public void emptyDescriptionExceptionTest() {
        EmptyDescriptionException exception = new EmptyDescriptionException("todo");
        assertEquals("Oops! TwoFive would like to remind you that the description of a todo cannot be empty.",
                exception.getMessage());
    }
}
