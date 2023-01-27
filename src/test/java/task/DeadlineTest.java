import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toString_singleInstance_correctRepresentation() {
        String dateTime = "2024-01-01 12:12";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dt = LocalDateTime.parse(dateTime, formatter);
        Deadline deadline = new Deadline("a", dt);
        String expectedMessage = "[D][ ] a (by: 2024-01-01 12:12)";
        String actualMessage = deadline.toString();
        assertEquals(expectedMessage, actualMessage);
    }
}
