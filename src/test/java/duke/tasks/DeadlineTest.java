package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    void hasCorrectDescription() {
        // Calculate date excatly after 24 hrs
        LocalDateTime date = LocalDateTime.now().plusDays(1);
        Deadline deadline = new Deadline(1, "return book", "tomorrow");
        assertEquals(String.format("[D][ ] return book (by: %s)",
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a"))), deadline.toString());
    }

    @Test
    void hasCorrectStatus() {
        Deadline deadline = new Deadline(1, "return book", "tomorrow");
        assertEquals(false, deadline.isCompleted());
        deadline.markCompleted();
        LocalDateTime date = LocalDateTime.now().plusDays(1);
        assertEquals(String.format("[D][X] return book (by: %s)",
                date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a"))), deadline.toString());

    }
}
