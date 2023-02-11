package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {

    @Test
    public void testDeadlineCreation() {
        LocalDate localDate = LocalDate.of(2019, 12, 10);
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")).toString();
        Deadline deadline = new Deadline("Buy milk", date);
        assertEquals("Buy milk", deadline.getDescription());
        assertEquals(date, deadline.getDate());
    }

    @Test
    public void testDeadlineCompletion() {
        LocalDate date = LocalDate.of(2019, 12, 10);
        Deadline deadline = new Deadline("Buy milk", date);
        deadline.markAsDone();
        assertTrue(deadline.getIsDone());
    }


}
