package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    public void stringTest() {
        var e = new Event("visiting", "10/10/10 1010",
                "11/10/10 1110", false);
        assertEquals("   [E][ ] visiting |from: "
                + "10 Oct 2010, 10:10" + " |to: "
                + "11 Oct 2010, 11:10|\n", e.toString());
    }
    @Test
    public void writeTest() {
        var e = new Event("visiting", "10/10/10 1010",
                "11/10/10 1110", false);
        assertEquals("E | 0 | visiting | 10/10/10 1010 | 11/10/10 1110\n",
                e.toWrite());
    }
}