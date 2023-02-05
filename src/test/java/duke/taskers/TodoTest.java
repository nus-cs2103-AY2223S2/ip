package duke.taskers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testStringPrint1() {
        Todo t = new Todo("complete tutorial", false, false);
        assertEquals("[T][ ][ ]complete tutorial", t.toString());
    }

    @Test
    public void testStringPrint2() {
        Todo t = new Todo("do something", true, false);
        assertEquals("[T][X][ ]do something", t.toString());
    }

    @Test
    public void testStatusTrueString() {
        Todo t = new Todo("eat mooncakes", true, false);
        assertEquals("TODO / 1 / 0 / eat mooncakes", t.formatStringForFile());
    }

    @Test
    public void testStatusFalseString() {
        Todo t = new Todo("run a marathon", false, false);
        assertEquals("TODO / 0 / 0 / run a marathon", t.formatStringForFile());
    }
}
