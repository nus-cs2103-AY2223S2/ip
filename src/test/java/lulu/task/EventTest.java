package lulu.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] run (from:  today  to:  tomorrow)",
                new Event("run ", " today ", " tomorrow").toString());
    }
}
