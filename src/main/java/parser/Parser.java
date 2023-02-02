package parser;

import ui.Ui;

import storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.stream.Stream;

import javafx.scene.layout.VBox;

import commands.AddCommand;
import commands.Command;
import commands.DateTimeCommand;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import tasklist.TaskList;

public class Parser {

	enum TaskType {
		TODO, DEADLINE, EVENT
	}

	/**
	 * Get command from input.
	 * 
	 * @param input
	 * @return Command
	 */
	public Command parseInput(String input) {
		String[] inputArr = input.split(" ");
		String opType = inputArr[0].toLowerCase();
		switch (opType) {
			case "bye":
				return new ExitCommand(inputArr);
			case "list":
				return new ListCommand(inputArr);
			case "datetime":
				return new DateTimeCommand(inputArr);
			case "mark":
			case "unmark":
				return new MarkCommand(inputArr, opType.startsWith("mark"));
			case "delete":
				return new DeleteCommand(inputArr);
			case "todo":
			case "deadline":
			case "event":
				return new AddCommand(inputArr);
			case "find":
				return new FindCommand(inputArr);
			default:
				return new Command(inputArr);
		}
	}

	/**
	 * Create task from record in saved file and add it to tasklist.
	 * 
	 * @param input           User text input.
	 * @param tasklist        All the tasks that are recorded.
	 * @param ui              A service to render the page of GUI.
	 * @param storage         A store that represents the data access object (DAO).
	 * @param dialogContainer A container that holds all the rows of labels.
	 */
	public void parseAndSetData(String input, TaskList tasklist, Ui ui, Storage storage, VBox dialogContainer) {
		String inputLower = input.toLowerCase();
		if (inputLower.isBlank()) {
			return;
		}
		assert inputLower.contains("\\|") : "Need to contain |";

		String[] inputArr = inputLower.split("\\|");
		TaskType operationType = inputLower.startsWith("t")
				? TaskType.TODO
				: inputLower.startsWith("d")
						? TaskType.DEADLINE
						: TaskType.EVENT;

		Task newTask = null;

		switch (operationType) {
			case TODO:
				newTask = new Todo(inputArr[2]);
				break;
			case DEADLINE:
				newTask = new Deadline(inputArr[2], parseDateTimeStr(inputArr[3], ui, storage, dialogContainer));
				break;
			case EVENT:
				newTask = new Event(inputArr[2],
						new LocalDateTime[] { parseDateTimeStr(inputArr[3], ui, storage, dialogContainer),
								parseDateTimeStr(inputArr[4], ui, storage, dialogContainer) });
				break;
			default:
				break;
		}
		if (inputArr[1].equals("1"))
			newTask.markAsDone();
		tasklist.add(newTask);
	}

	/**
	 * Convert String datetime to LocalDateTime object.
	 * 
	 * @param dateTimeStr     Date time in string format.
	 * @param ui              A service to render the page of GUI.
	 * @param storage         A store that represents the data access object (DAO).
	 * @param dialogContainer A container that holds all the rows of labels.
	 * @return LocalDateTime
	 */
	public LocalDateTime parseDateTimeStr(String dateTimeStr, Ui ui, Storage storage, VBox dialogContainer) {
		if (dateTimeStr.isBlank()) {
			return null;
		}
		String[] dateTimeArr = dateTimeStr.split(" ");
		LocalTime time = LocalTime.MIN;
		Integer[] dateArr;
		String errorMessage = "Oops! Datetime format needs to be specified in proper form.";

		assert dateTimeArr.length > 0 : "Datetime must not be empty";
		if (dateTimeArr.length > 1) {
			try {
				int hr = Integer.parseInt(dateTimeArr[1].substring(0, 2));
				int min = Integer.parseInt(dateTimeArr[1].substring(2, 4));
				time = LocalTime.of(hr, min);
			} catch (NumberFormatException ex) {
				ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
				return null;
			}
		}

		try {
			if (dateTimeStr.contains("/")) { // Input Format: dd/MM/YYYY
				dateArr = Stream.of(dateTimeArr[0].split("/"))
						.map(Integer::valueOf)
						.toArray(Integer[]::new);
				return LocalDateTime.of(LocalDate.of(dateArr[2], dateArr[1], dateArr[0]), time);
			}

			// Input Format: YYYY-MM-dd
			dateArr = Stream.of(dateTimeArr[0].split("-"))
					.map(Integer::valueOf)
					.toArray(Integer[]::new);
			return LocalDateTime.of(LocalDate.of(dateArr[0], dateArr[1], dateArr[2]), time);
		} catch (ArrayIndexOutOfBoundsException ex) {
			ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
			return null;
		}

	}

	/**
	 * Get a datetime object from deadline tasks.
	 * 
	 * @param inputArr        An array of string that is split from user text input.
	 * @param ui              A service to render the page of GUI.
	 * @param storage         A store that represents the data access object (DAO).
	 * @param dialogContainer A container that holds all the rows of labels.
	 * @return LocalDateTime
	 */
	public LocalDateTime getBy(String[] inputArr, Ui ui, Storage storage, VBox dialogContainer) {
		int l = inputArr.length;
		for (int i = 0; i < l; i++) {
			if (i < l - 1 && inputArr[i].equals("/by")) {
				return parseDateTimeStr(sliceArrAndConcate(inputArr, i + 1, l), ui, storage, dialogContainer);
			}
		}
		String errorMessage = "Oops! The description of a DEADLINE must include a BY datetime";
		ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
		return null;
	}

	/**
	 * Get a datetime array object from event tasks.
	 * 
	 * @param inputArr        An array of string that is split from user text input.
	 * @param ui              A service to render the page of GUI.
	 * @param storage         A store that represents the data access object (DAO).
	 * @param dialogContainer A container that holds all the rows of labels.
	 * @return LocalDateTime[]
	 */
	public LocalDateTime[] getFromTo(String[] inputArr, Ui ui, Storage storage, VBox dialogContainer) {
		int l = inputArr.length;
		int fromStartIdx = -1, fromEndIdx = l, toStartIdx = -1;
		LocalDateTime from = null;
		LocalDateTime to = null;
		for (int i = 0; i < l; i++) {
			if (i < l - 1) {
				if (inputArr[i].equals("/from")) {
					fromStartIdx = i + 1;
				}
				if (inputArr[i].equals("/to")) {
					fromEndIdx = i;
					toStartIdx = i + 1;
				}
			}
		}
		if (fromStartIdx > -1) {
			from = parseDateTimeStr(sliceArrAndConcate(inputArr, fromStartIdx, fromEndIdx), ui, storage,
					dialogContainer);
			if (from != null && toStartIdx > -1) {
				to = parseDateTimeStr(sliceArrAndConcate(inputArr, toStartIdx, l), ui, storage, dialogContainer);
			}
		}
		return new LocalDateTime[] { from, to };
	}

	/**
	 * Remove the datetime string from an input string.
	 * 
	 * @param input User text input.
	 * @return String
	 */
	public String trimDate(String input) {
		String[] inputArr = input.split(" ");
		StringBuilder sb = new StringBuilder();
		for (String s : inputArr) {
			if (s.contains("/")) {
				return sb.toString().trim();
			}
			sb.append(s + " ");
		}
		return sb.toString().trim();
	}

	/**
	 * Form a string from a string array with a specific start and end index.
	 * 
	 * @param arr
	 * @param startIdx
	 * @param endIdx
	 * @return String
	 */
	public String sliceArrAndConcate(String[] arr, int startIdx, int endIdx) {
		return String.join(" ", Arrays.copyOfRange(arr, startIdx, endIdx));
	}

}
