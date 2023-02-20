package tigerlily.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void outputTest() {
        Deadline testDeadline = new Deadline("test", LocalDate.parse("2023-02-14", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertEquals("[D][ ] test (by: 14 February 2023)", testDeadline.toString());
    }
}