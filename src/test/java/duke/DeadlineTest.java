package duke;

import org.junit.jupiter.api.Test;

import model.tasks.Deadline;
import model.tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {
    @Test
    public void testToString1() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
		LocalDateTime date = LocalDateTime.parse("24-11-2023 1200", formatter);
        Task deadline = new Deadline("See the sunrise", date, false);
        String result = deadline.toString();
        assertEquals("[D][  ] See the sunrise (by: Nov 24 2023, 1200)", result);
    }
}
