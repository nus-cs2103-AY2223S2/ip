package cbot;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class CbotTest {
    @Test
    public void testIsBye() {
        Cbot cbot = new Cbot();
        assertFalse(cbot.isBye());
    }

    @Test
    public void testIsBad() {
        Cbot cbot = new Cbot();
        assertFalse(cbot.isBye());
    }
}
