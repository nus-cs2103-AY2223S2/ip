package AddTasks;
import munch.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlinesTest {
    @Test
    public void testDeadlineTasks() {
        String date = "01/02/2023";
        LocalDate convertDate = Parser.convertToDate(date);
        Deadlines deadline1 = new Deadlines("read book", convertDate );
        assertEquals(" [D][ ] read book (By: Feb 1 2023)", deadline1.toString());

        Deadlines deadline2 = new Deadlines("read book", convertDate);
        deadline2.markAsDone();
        assertEquals(" [D][X] read book (By: Feb 1 2023)", deadline2.toString());
        deadline2.markAsUndone();
        assertEquals(" [D][ ] read book (By: Feb 1 2023)", deadline2.toString());
    }
}
