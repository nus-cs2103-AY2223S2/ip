package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testToDiskFormat() {
        assertEquals("D|0|project meeting|2023-02-02-02-09",
                new Deadline("project meeting", "2023-02-02-02-09").toDiskFormat());
        assertEquals("D|1|project meeting|2023-02-02-02-09",
                new Deadline("project meeting", "2023-02-02-02-09", true).toDiskFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[ ][D] project meeting (by: 04 Aug 2015, 10.11 AM)",
                new Deadline("project meeting", "2015-08-04-10-11").toString());
        assertEquals("[X][D] project meeting (by: 04 Aug 2015, 10.11 AM)",
                new Deadline("project meeting", "2015-08-04-10-11", true).toString());
    }
}
