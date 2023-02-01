package handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JEchoTest {
    @Test
    public void test1() {
        String s = "what ever you input";
        JEcho echo = new JEcho();
        assertTrue(echo.canTake(s));
        assertEquals(s, echo.take(s));
    }
}
