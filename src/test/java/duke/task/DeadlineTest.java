package duke.task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {

    @Test
    public void testToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse("2021-11-12 09:00", formatter);
        Task deadline = new Deadline("eat", date);
        assertEquals(deadline.toString(), "[D][ ] eat (by: Nov 12 2021 09:00)");
    }

    @Test
    public void testMark() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse("2021-11-12 09:00", formatter);
        Task deadline = new Deadline("eat", date);
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[D][X] eat (by: Nov 12 2021 09:00)");
    }
}
