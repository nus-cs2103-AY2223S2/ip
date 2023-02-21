package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private final Deadline deadline1 = new Deadline("some description", "01/01/2001 12:12");
    private final Deadline deadline2 = new Deadline("another description", "02/02/2002 12:12");
    @Test
    public void printTask() {
        assertEquals(deadline1.printTask(), "[D][ ] some description (by: 2001 Jan 01, 12:12)");
        deadline2.markTaskDone(true);
        assertEquals(deadline2.printTask(), "[D][X] another description (by: 2002 Feb 02, 12:12)");
    }

    @Test
    public void formatForFile() {
        assertEquals(deadline1.formatForFile(), "D|O|some description|01/01/2001 12:12");
        deadline2.markTaskDone(true);
        assertEquals(deadline2.formatForFile(), "D|X|another description|02/02/2002 12:12");
    }

    @Test
    public void printDateTime() {
        assertEquals(deadline1.printDateTime(), "2001 Jan 01, 12:12");
        assertEquals(deadline2.printDateTime(), "2002 Feb 02, 12:12");
    }

    @Test
    public void isDeadLine() {
        MyDate date = new MyDate("01/01/2001");
        assertTrue(deadline1.isDeadLine(date));
        assertFalse(deadline2.isDeadLine(date));
    }
}
