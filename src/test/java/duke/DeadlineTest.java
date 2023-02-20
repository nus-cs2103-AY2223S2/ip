package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



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

    @Test
    public void testDeadlineToString() {
        LocalDate date = LocalDate.of(2019, 12, 10);
        Deadline deadline = new Deadline("Buy milk", date);
        assertEquals("[D][ ] Buy milk (by: Dec 10 2019)", deadline.toString());
    }

    @Test
    public void testDeadlineToStringCompletion() {
        LocalDate date = LocalDate.of(2019, 12, 10);
        Deadline deadline = new Deadline("Buy milk", date);
        deadline.markAsDone();
        assertEquals("[D][X] Buy milk (by: Dec 10 2019)", deadline.toString());
    }

    

}
