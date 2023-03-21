package cbot.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandTest {
    @Test
    public void testNeedSaveTrue() {
        assertTrue(Command.SORT.needSave());
    }

    @Test
    public void testNeedSaveFalse() {
        assertFalse(Command.BEFORE.needSave());
    }

    @Test
    public void testGetMatch00() {
        assertFalse(Command.SORT.matches("sor"));
    }

    @Test
    public void testGetMatch01() {
        assertTrue(Command.SORT.matches("sOrT"));
    }

    @Test
    public void testGetMatch10() {
        assertFalse(Command.BEFORE.matches("before"));
    }

    @Test
    public void testGetMatch11() {
        assertTrue(Command.BEFORE.matches("before a"));
    }

    @Test
    public void testGetMatch12() {
        assertTrue(Command.BEFORE.matches("bef a"));
    }
}
