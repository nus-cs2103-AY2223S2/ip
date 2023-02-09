package roody;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import roody.tasks.Deadline;
import roody.tasks.Task;

public class DeadlineTest {
    @Test
    public void testType() {
        assertEquals(new Deadline("", LocalDate.now()).getType(), 'D');
    }

    @Test
    public void testLocalDateConversion() {
        LocalDate date = LocalDate.now();
        Task deadline = new Deadline("", date);
        String testString = " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        assertEquals(deadline.toString(), testString);
    }

    @Test
    public void testSavingTask() {
        LocalDate date = LocalDate.now();
        Task deadline = new Deadline("sleep", date);
        String testString = "sleep|false|D|" + date.toString();
        assertEquals(deadline.saveTask(), testString);
    }
}
