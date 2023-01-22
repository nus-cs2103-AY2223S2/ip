package parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import commands.AddCommand;
import commands.Command;
import commands.DateTimeCommand;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.MarkCommand;
import ui.Ui;

public class ParserTest {

	Parser parser = new Parser();
	Ui ui = new Ui();

	String[] inputArrDeadline = new String[] {
			"deadline", "work", "on", "programming",
			"assignment", "/by", "2019-03-01", "0815" };

	String[] inputArrEvent = new String[] {
			"event", "clean", "up", "toilet",
			"/from", "2019-03-01", "0815",
			"/to", "2019-03-01", "1030" };

	@Test
	public void parseInputExitTest() {
		String input = "bye";
		Command actualCommand = parser.parseInput(input);
		assertTrue(actualCommand instanceof ExitCommand);
	}

	@Test
	public void parseInputListTest() {
		String input = "list";
		Command actualCommand = parser.parseInput(input);
		assertTrue(actualCommand instanceof ListCommand);
	}

	@Test
	public void parseInputDateTimeTest() {
		String input = "datetime 2019-12-02 1800";
		Command actualCommand = parser.parseInput(input);
		assertTrue(actualCommand instanceof DateTimeCommand);
	}

	@Test
	public void parseInputMarkTest() {
		String markInput = "mark 1";
		String unmarkInput = "unmark 2";
		Command actualMarkCommand = parser.parseInput(markInput);
		Command actualUnmarkCommand = parser.parseInput(unmarkInput);
		assertTrue(actualMarkCommand instanceof MarkCommand);
		assertTrue(actualUnmarkCommand instanceof MarkCommand);

		MarkCommand actualMarkCommand_ = (MarkCommand) actualMarkCommand;
		MarkCommand actualUnmarkCommand_ = (MarkCommand) actualUnmarkCommand;
		assertTrue(actualMarkCommand_.getIsMark());
		assertTrue(!actualUnmarkCommand_.getIsMark());
	}

	@Test
	public void parseInputDeleteTest() {
		String input = "delete 1";
		Command actualCommand = parser.parseInput(input);
		assertTrue(actualCommand instanceof DeleteCommand);
	}

	@Test
	public void parseInputAddTest() {
		String todoInput = "todo read book";
		String deadlineInput = "deadline return book /by 2019-03-01 0815";
		String eventInput = "event project meeting /from 2/12/2019 1800 /to 2019-12-02 2200";
		String[] todoInputArr = todoInput.split(" ");
		String[] deadlineInputArr = deadlineInput.split(" ");
		String[] eventInputArr = eventInput.split(" ");

		// Test todo operation
		AddCommand expectedCommand = new AddCommand(todoInputArr);
		Command actualCommand = parser.parseInput(todoInput);
		assertTrue(actualCommand instanceof AddCommand);
		AddCommand actualAddCommand = (AddCommand) actualCommand;
		assertEquals(expectedCommand.getTaskType(), actualAddCommand.getTaskType());

		// Test deadline operation
		expectedCommand = new AddCommand(deadlineInputArr);
		actualCommand = parser.parseInput(deadlineInput);
		assertTrue(actualCommand instanceof AddCommand);
		actualAddCommand = (AddCommand) actualCommand;
		assertEquals(expectedCommand.getTaskType(), actualAddCommand.getTaskType());

		// Test event operation
		expectedCommand = new AddCommand(eventInputArr);
		actualCommand = parser.parseInput(eventInput);
		assertTrue(actualCommand instanceof AddCommand);
		actualAddCommand = (AddCommand) actualCommand;
		assertEquals(expectedCommand.getTaskType(), actualAddCommand.getTaskType());
	}

	@Test
	public void parseDateStrTest() {
		String dateFormat1 = "2019-12-02";
		String dateFormat2 = "2/12/2019";
		LocalDateTime expected = LocalDateTime.of(LocalDate.of(2019, 12, 2), LocalTime.MIN);
		assertEquals(expected, parser.parseDateTimeStr(dateFormat1, ui));
		assertEquals(expected, parser.parseDateTimeStr(dateFormat2, ui));
	}

	@Test
	public void parseDateTimeStrTest() {
		String dateTimeFormat1 = "2019-12-02 1530";
		String dateTimeFormat2 = "2/12/2019 1530";
		LocalDateTime expected = LocalDateTime.of(LocalDate.of(2019, 12, 2), LocalTime.of(15, 30));
		assertEquals(expected, parser.parseDateTimeStr(dateTimeFormat1, ui));
		assertEquals(expected, parser.parseDateTimeStr(dateTimeFormat2, ui));
	}

	@Test
	public void getByTest() {

		LocalDateTime expected = LocalDateTime.of(
				LocalDate.of(2019, 3, 1), LocalTime.of(8, 15));
		assertEquals(expected, parser.getBy(inputArrDeadline, ui));
	}

	@Test
	public void getFromToTest() {
		LocalDateTime[] expected = new LocalDateTime[] {
				LocalDateTime.of(
						LocalDate.of(2019, 3, 1), LocalTime.of(8, 15)),
				LocalDateTime.of(
						LocalDate.of(2019, 3, 1), LocalTime.of(10, 30)),
		};
		assertArrayEquals(expected, parser.getFromTo(inputArrEvent, ui));
	}

	@Test
	public void trimDateTest() {
		String input1 = "return book /by 2019-03-01 0815";
		String input2 = "play table tennis /from 2019-12-02 1600 /to 2019-12-02 1800";
		assertEquals("return book", parser.trimDate(input1));
		assertEquals("play table tennis", parser.trimDate(input2));
	}

	@Test
	public void sliceArrAndConcateTest() {
		assertEquals("2019-03-01 0815",
				parser.sliceArrAndConcate(inputArrDeadline, 6, 8));
	}

}
