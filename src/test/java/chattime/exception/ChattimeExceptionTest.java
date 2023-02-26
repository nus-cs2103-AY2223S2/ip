package chattime.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ChattimeExceptionTest {
    @Test
    public void getMessageTest() {
        try {
            throw new ChattimeException("Test");
        } catch (ChattimeException e) {
            assertEquals("OOPS!!! Test", e.getMessage());
        }
    }

    @Test
    public void toStringTest() {
        try {
            throw new ChattimeException("Test");
        } catch (ChattimeException e) {
            assertEquals("OOPS!!! Test", e.toString());
        }
    }
}
