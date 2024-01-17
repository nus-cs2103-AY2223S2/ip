package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {

    @Test
    public void toString_singleInstance_correctRepresentation() {
        String descr = "Deadline test";
        String dateTime = "2024-01-01 01:01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dt = LocalDateTime.parse(dateTime, formatter);
        Deadline deadline = new Deadline(descr, dt);
        String actualMessage = deadline.toString();
        String expectedMessage = "Deadline test (by: 2024-01-01 01:01)";
        assertEquals(expectedMessage, actualMessage);
    }
}
