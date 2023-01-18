package seedu.shao;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class ShaoTest {

	String[] inputArrDeadline = new String[] {
			"deadline", "work", "on", "programming",
			"assignment", "/by", "2019-03-01", "0815" };

	String[] inputArrEvent = new String[] {
			"event", "clean", "up", "toilet",
			"/from", "2019-03-01", "0815",
			"/to", "2019-03-01", "1030" };

	@Test
	public void parseDateStrTest() {
		String dateFormat1 = "2019-12-02";
		String dateFormat2 = "2/12/2019";
		LocalDateTime expected = LocalDateTime.of(LocalDate.of(2019, 12, 2), LocalTime.MIN);
		assertEquals(expected, Shao.parseDateTimeStr(dateFormat1));
		assertEquals(expected, Shao.parseDateTimeStr(dateFormat2));
	}

	@Test
	public void parseDateTimeStrTest() {
		String dateTimeFormat1 = "2019-12-02 1530";
		String dateTimeFormat2 = "2/12/2019 1530";
		LocalDateTime expected = LocalDateTime.of(LocalDate.of(2019, 12, 2), LocalTime.of(15, 30));
		assertEquals(expected, Shao.parseDateTimeStr(dateTimeFormat1));
		assertEquals(expected, Shao.parseDateTimeStr(dateTimeFormat2));
	}

	@Test
	public void trimDateTest() {
		String input1 = "return book /by 2019-03-01 0815";
		String input2 = "play table tennis /from 2019-12-02 1600 /to 2019-12-02 1800";
		assertEquals("return book", Shao.trimDate(input1));
		assertEquals("play table tennis", Shao.trimDate(input2));
	}

	@Test
	public void sliceArrAndConcateTest() {
		assertEquals("2019-03-01 0815",
				Shao.sliceArrAndConcate(inputArrDeadline, 6, 8));
	}

	@Test
	public void getByTest() {

		LocalDateTime expected = LocalDateTime.of(
				LocalDate.of(2019, 3, 1), LocalTime.of(8, 15));
		assertEquals(expected, Shao.getBy(inputArrDeadline));
	}

	@Test
	public void getFromToTest() {
		LocalDateTime[] expected = new LocalDateTime[] {
				LocalDateTime.of(
						LocalDate.of(2019, 3, 1), LocalTime.of(8, 15)),
				LocalDateTime.of(
						LocalDate.of(2019, 3, 1), LocalTime.of(10, 30)),
		};
		assertArrayEquals(expected, Shao.getFromTo(inputArrEvent));
	}
}
