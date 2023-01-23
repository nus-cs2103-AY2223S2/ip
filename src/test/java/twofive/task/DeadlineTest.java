package twofive.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineStringTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime deadline = LocalDateTime.parse("2023-06-06 06:06", formatter);
        Deadline deadlineTask = new Deadline("return book", deadline);
        assertEquals("[D][ ] return book (by: Tue Jun 6 2023 06:06AM)", deadlineTask.toString());
    }

    @Test
    public void deadlineFileWriteStringTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime deadline = LocalDateTime.parse("2023-06-06 06:06", formatter);
        Deadline deadlineTask = new Deadline("return book", deadline);
        assertEquals("D | 0 | return book | 2023-06-06 06:06", deadlineTask.getFileWriteString());
    }

    @Test
    public void deadlineIsTodayTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime deadline = LocalDateTime.parse("2023-06-06 06:06", formatter);
        Deadline deadlineTask = new Deadline("return book", deadline);
        LocalDate differentDate = LocalDate.parse("2023-01-23", dateOnlyFormatter);
        assertEquals(false, deadlineTask.isToday(differentDate));
    }
}
