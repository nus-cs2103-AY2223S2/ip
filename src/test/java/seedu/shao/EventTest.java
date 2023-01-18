package seedu.shao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventTest {
	LocalDateTime from = LocalDateTime.of(LocalDate.of(2023, 1, 18),
			LocalTime.of(14, 15));
	LocalDateTime to = LocalDateTime.of(LocalDate.of(2023, 1, 19),
			LocalTime.of(15, 30));
	LocalDateTime[] fromTo = new LocalDateTime[] { from, to };
	Event event = new Event("Attend field camp", fromTo);

	@Test
	public void toStringTest() {
		String expected = "[E][ ] Attend field camp (from: Jan 18 2023, to: Jan 19 2023)";
		assertEquals(expected, event.toString());
	}

	// @Test
	// public void getSavedFormatTest() {
	// event.markAsDone();
	// String expected = "E|1|Attend field camp|";
	// assertEquals(expected, todo.getSavedFormat());
	// }
}
