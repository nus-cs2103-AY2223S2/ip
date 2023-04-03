package duke;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        LocalDate date = LocalDate.parse("2023-01-26");
        Deadline deadline = new Deadline(date, "homework");
        assertEquals("[D][ ] homework (by: 26 Jan 2023)", deadline.toString());
    }

}
