package chattime.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ChattimeExceptionTest {
    @Test
    public void toStringTest() {
        try {
            throw new ChattimeException("Test");
        } catch (ChattimeException e) {
            assertEquals("Test", e.toString());
        }
    }
}
