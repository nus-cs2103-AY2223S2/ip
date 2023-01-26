package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToDiskFormat() {
        assertEquals("D|0|project meeting|2015-08-04T10:11:30",
                new Deadline("project meeting", "2015-08-04T10:11:30").toDiskFormat());
        assertEquals("D|1|project meeting|2015-08-04T10:11:30",
                new Deadline("project meeting", "2015-08-04T10:11:30", true).toDiskFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[ ][D] project meeting (by: 04 Aug 2015, 10.11 AM)",
                new Deadline("project meeting", "2015-08-04T10:11:30").toString());
        assertEquals("[X][D] project meeting (by: 04 Aug 2015, 10.11 AM)",
                new Deadline("project meeting", "2015-08-04T10:11:30", true).toString());
    }
}
