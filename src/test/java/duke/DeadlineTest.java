package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void descriptionTest() {
        Deadline deadline = new Deadline("Eat lunch", "2023-02-04 14:00");
        assertEquals("Eat lunch", deadline.getDescription());
    }

    @Test
    public void byTimeTest() {
        Deadline deadline = new Deadline("Eat lunch", "2023-02-04 14:00");
        assertEquals("2023-02-04 14:00", deadline.getBy());
    }

    @Test
    public void notDoneStatusIconTest() {
        Deadline deadline = new Deadline("Eat lunch", "2023-02-04 14:00");
        assertEquals(" ", deadline.getStatusIcon());
    }

    @Test
    public void doneStatusIconTest() {
        Deadline deadline = new Deadline("Eat lunch", "2023-02-04 14:00");
        deadline.setDone(true, LocalDateTime.now());
        assertEquals("X", deadline.getStatusIcon());
    }
}
