package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        LocalDate date = LocalDate.parse("19/10/2023", DateTimeFormatter.ofPattern("d/MM/yyyy"));
        Deadline d = new Deadline("grocery", date);
        String test = "[D][ ] grocery (by: 19 Oct 2023)";
        assertEquals(d.toString(), test);
    }

    @Test
    public void equalsTest() {
        LocalDate date = LocalDate.parse("19/10/2023", DateTimeFormatter.ofPattern("d/MM/yyyy"));
        Deadline d = new Deadline("grocery", date);
        Deadline test = new Deadline("grocery", date);
        assertEquals(d, test);
    }

    @Test
    public void toDataFormatStringTest() {
        LocalDate date = LocalDate.parse("19/10/2023", DateTimeFormatter.ofPattern("d/MM/yyyy"));
        Deadline d = new Deadline("grocery", date, true);
        String test = "D / 1 / grocery - 19/10/2023";
        assertEquals(d.toDataFormatString(), test);
    }
}

