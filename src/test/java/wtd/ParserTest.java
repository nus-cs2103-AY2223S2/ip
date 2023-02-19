package wtd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import wtd.exceptions.WtdException;

public class ParserTest {
    @Test
    public void testParse() {
        try {
            assertEquals("todo", Parser.parse("todo test").getCommand());
            assertEquals("deadline", Parser.parse("deadline test /by 2020-01-01").getCommand());
            assertEquals("event", Parser.parse("event test /from 2020-01-01 /to 2020-01-02").getCommand());
            assertEquals("list", Parser.parse("list").getCommand());
            assertEquals("mark", Parser.parse("mark 1").getCommand());
            assertEquals("unmark", Parser.parse("unmark 1").getCommand());
            assertEquals("remove", Parser.parse("remove 1").getCommand());
            assertEquals("find", Parser.parse("find test").getCommand());
            assertEquals("bye", Parser.parse("bye").getCommand());
        } catch (WtdException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParseInvalidCommand() {
        try {
            Parser.parse("test");
            fail("WtdException not thrown");
        } catch (WtdException e) {
            assertEquals("Stop messing around! What does that mean? I don't understand.", e.getMessage());
        }
    }

    @Test
    public void testParseDate() {
        try {
            assertEquals(LocalDate.parse("2020-01-01"), Parser.parseDate("2020-01-01"));
        } catch (WtdException e) {
            fail(e.getMessage());
        }
    }
}
