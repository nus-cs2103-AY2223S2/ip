package duke.task;

import duke.DukeUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    @Test
    public void mark() {
        DukeTask deadline = new DeadlineTask("task one",
                DukeUtils.parseDateTime("2/2/23 0000"));
        deadline.markUndone();
        assertEquals("[D][ ] task one (by: Feb 02 2023 12:00AM)", deadline.toString());
    }

    @Test
    public void unMark() {
        DukeTask deadline = new DeadlineTask("task one",
                DukeUtils.parseDateTime("2/2/23 0000"));
        deadline.setDone();
        assertEquals("[D][X] task one (by: Feb 02 2023 12:00AM)", deadline.toString());
    }

    @Test
    public void isOnDate() {
        DukeTask deadline = new DeadlineTask("task one",
                DukeUtils.parseDateTime("2/2/23 0000"));
        assertEquals(true,
                deadline.isOnDate(DukeUtils.parseDate("2/2/23")));
    }

    @Test
    public void isNotOnDate() {
        DukeTask deadline = new DeadlineTask("task one",
                DukeUtils.parseDateTime("2/2/23 0000"));
        assertEquals(false,
                deadline.isOnDate(DukeUtils.parseDate("1/2/23")));
    }

    @Test
    public void toDbSchema() {
        DukeTask deadline = new DeadlineTask("task one",
                DukeUtils.parseDateTime("2/2/23 0000"));
        assertEquals(
                "D|0|task one|2/2/23 0000",
                deadline.toDbSchema()
        );
    }
}
