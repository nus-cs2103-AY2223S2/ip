package tasks;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void descriptionTest() throws DukeException {
        String name = "should output this";
        LocalDateTime date = LocalDateTime.parse("10/12/2022 1020", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        Deadline test = new Deadline(name, date);
        assertEquals("[D][ ] should output this (by 1020, Dec 10 2022)", test.toString());
    }

    @Test
    public void markTest() throws DukeException {
        String name = "should output this";
        LocalDateTime date = LocalDateTime.parse("10/12/2022 1020", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        Deadline test = new Deadline(name, date);
        test.markAsDone();
        assertEquals("[D][X] should output this (by 1020, Dec 10 2022)", test.toString());
        test.markAsUndone();
        assertEquals("[D][ ] should output this (by 1020, Dec 10 2022)", test.toString());
    }

}
