package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testToDiskFormat() {
        assertEquals("E|0|project meeting|2023-02-02-02-09|2023-02-02-02-09",
                new Event("project meeting", "2023-02-02-02-09", "2023-02-02-02-09").toDiskFormat());
        assertEquals("E|1|project meeting|2023-02-02-02-09|2023-02-02-02-09",
                new Event("project meeting", "2023-02-02-02-09", "2023-02-02-02-09", true).toDiskFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[ ][E] project meeting (from: 04 Aug 2015, 10.11 AM to 04 Aug 2015, 10.11 AM)",
                new Event("project meeting", "2015-08-04-10-11", "2015-08-04-10-11").toString());
        assertEquals("[X][E] project meeting (from: 04 Aug 2015, 10.11 AM to 04 Aug 2015, 10.11 AM)",
                new Event("project meeting", "2015-08-04-10-11", "2015-08-04-10-11", true).toString());
    }
}
