package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        LocalDate from = LocalDate.parse("19/10/2023", DateTimeFormatter.ofPattern("d/MM/yyyy"));
        LocalDate to = LocalDate.parse("21/10/2023", DateTimeFormatter.ofPattern("d/MM/yyyy"));
        Event e = new Event("grocery", from, to);
        String test = "[E][ ] grocery (from: 19 Oct 2023 to: 21 Oct 2023)";
        assertEquals(e.toString(), test);
    }

    @Test
    public void equalsTest() {
        LocalDate from = LocalDate.parse("19/10/2023", DateTimeFormatter.ofPattern("d/MM/yyyy"));
        LocalDate to = LocalDate.parse("21/10/2023", DateTimeFormatter.ofPattern("d/MM/yyyy"));
        Event e = new Event("grocery", from, to);
        Event test = new Event("grocery", from, to);
        assertEquals(e, test);
    }

    @Test
    public void toDataFormatStringTest() {
        LocalDate from = LocalDate.parse("19/10/2023", DateTimeFormatter.ofPattern("d/MM/yyyy"));
        LocalDate to = LocalDate.parse("21/10/2023", DateTimeFormatter.ofPattern("d/MM/yyyy"));
        Event e = new Event("grocery", from, to, true);
        String test = "E / 1 / grocery - 19/10/2023 - 21/10/2023";
        assertEquals(e.toDataFormatString(), test);
    }
}
