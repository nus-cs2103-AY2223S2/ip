package duke.task;

import duke.DukeUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {
    @Test
    public void mark() {
        DukeTask event = new EventTask(
                "task one",
                DukeUtils.parseDateTime("2/2/23 0001"),
                DukeUtils.parseDateTime("2/2/23 0005")
        );
        event.setDone();
        assertEquals(
                "[E][X] task one (from: Feb 2 2023 12:01AM to: Feb 2 2023 12:05AM)",
                event.toString()
        );
    }

    @Test
    public void unMark() {
        DukeTask event = new EventTask(
                "task one",
                DukeUtils.parseDateTime("2/2/23 0001"),
                DukeUtils.parseDateTime("2/2/23 0005")
        );

        assertEquals(
                "[E][ ] task one (from: Feb 2 2023 12:01AM to: Feb 2 2023 12:05AM)",
                event.toString()
        );
    }

    @Test
    public void isOnDate() {
        DukeTask event = new EventTask(
                "task one",
                DukeUtils.parseDateTime("2/2/23 0001"),
                DukeUtils.parseDateTime("2/2/23 0005")
        );
        assertEquals(true,
                event.isOnDate(DukeUtils.parseDate("2/2/23")));
    }

    @Test
    public void isNotOnDate() {
        DukeTask event = new EventTask(
                "task one",
                DukeUtils.parseDateTime("2/2/23 0001"),
                DukeUtils.parseDateTime("2/2/23 0005")
        );
        assertEquals(false,
                event.isOnDate(DukeUtils.parseDate("1/2/23")));
    }

    @Test
    public void toDbSchema() {
        DukeTask event = new EventTask(
                "task one",
                DukeUtils.parseDateTime("2/2/23 0001"),
                DukeUtils.parseDateTime("2/2/23 0005")
        );
        assertEquals(
                "E|0|task one|2/2/23 0001|2/2/23 0005",
                event.toDbSchema()
        );
    }
}
