package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToDiskFormat() {
        assertEquals("E|0|project meeting|2015-08-04T10:11:30|2015-08-04T10:11:30",
                new Event("project meeting", "2015-08-04T10:11:30","2015-08-04T10:11:30").toDiskFormat());
        assertEquals("E|1|project meeting|2015-08-04T10:11:30|2015-08-04T10:11:30",
                new Event("project meeting", "2015-08-04T10:11:30","2015-08-04T10:11:30", true).toDiskFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[ ][E] project meeting (from: 04 Aug 2015, 10.11 AM to 04 Aug 2015, 10.11 AM)",
                new Event("project meeting", "2015-08-04T10:11:30","2015-08-04T10:11:30").toString());
        assertEquals("[X][E] project meeting (from: 04 Aug 2015, 10.11 AM to 04 Aug 2015, 10.11 AM)",
                new Event("project meeting", "2015-08-04T10:11:30","2015-08-04T10:11:30", true).toString());
    }
}
