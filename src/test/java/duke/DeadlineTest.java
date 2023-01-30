package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void test() {
        LocalDate date1 = LocalDate.parse("13/01/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Deadline deadline1 = new Deadline("homework", date1);
        assertEquals("[D][ ] homework (by: Jan 13 2023)", deadline1.toString());

        LocalDate date2 = LocalDate.parse("19/01/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Deadline deadline2 = new Deadline("submission", date2);
        deadline2.markAsDone();
        assertEquals("[D][X] submission (by: Jan 19 2023)", deadline2.toString());

        Deadline deadline3 = new Deadline("", date2);
        assertEquals("[D][ ]  (by: Jan 19 2023)", deadline3.toString());
    }
}
