package lulu.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] run (by:  tomorrow)", new Deadline("run ", " tomorrow").toString());
    }
}
