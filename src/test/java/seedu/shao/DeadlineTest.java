package seedu.shao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DeadlineTest {
	LocalDateTime by = LocalDateTime.of(LocalDate.of(2023, 1, 19),
			LocalTime.of(22, 30));
	Deadline deadline = new Deadline("Attend field camp", by);

	@Test
	public void toStringTest() {
		String expected = "[D][ ] Attend field camp (by: Jan 19 2023 10:30 pm)";
		assertEquals(expected, deadline.toString());
	}

	@Test
	public void getSavedFormatTest() {
		deadline.markAsDone();
		String expected = "D|1|Attend field camp|2023-01-19 2230";
		assertEquals(expected, deadline.getSavedFormat());
	}
}
