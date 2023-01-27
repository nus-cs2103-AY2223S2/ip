package genie.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void testToStringConversion() {
        assertEquals("[D][ ] do homework (by: Sat, 28/01/2023, 11:59 PM)",
                new Deadline("do homework", "2023-01-28 23:59").toString());
        assertEquals("[D][ ] do homework (by: 20/3 4pm)",
                new Deadline("do homework", "20/3 4pm").toString());
        Deadline d = new Deadline("watch lecture", "2023-01-28 23:59");
        d.markDone();
        assertEquals("[D][X] watch lecture (by: Sat, 28/01/2023, 11:59 PM)", d.toString());
        d.unmarkDone();
        assertEquals("[D][ ] watch lecture (by: Sat, 28/01/2023, 11:59 PM)", d.toString());
    }
    @Test
    public void testToFileFormatConversion() {
        assertEquals("[D][ ] homework | Sat, 28/01/2023, 8:00 PM",
                new Deadline("homework", "2023-01-28 20:00").toFileFormat());
        assertEquals("[D][ ] homework | 28/1 5pm",
                new Deadline("homework", "28/1 5pm").toFileFormat());
    }
}