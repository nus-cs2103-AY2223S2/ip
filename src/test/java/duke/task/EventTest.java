package duke.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event eventTask;

    @BeforeEach
    public void setUp() {
        eventTask = new Event("event return book /from 2020-10-10 /to 2021-10-10",
                LocalDate.parse("2020-10-10"), LocalDate.parse("2021-10-10"));
    }

    @Test
    public void getTaskTest() {
        assertEquals("return book", eventTask.getTask());
    }

    @Test
    public void getTimelineTest() {
        assertEquals( "Oct 10 2020 to Oct 10 2021", eventTask.getTimeline());
    }
}
