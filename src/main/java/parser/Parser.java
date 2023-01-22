package parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.stream.Stream;

import commands.AddCommand;
import commands.Command;
import commands.DateTimeCommand;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.MarkCommand;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import tasklist.TaskList;
import ui.Ui;

public class Parser {

	enum TaskType {
		TODO, DEADLINE, EVENT
	}

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
			default:
				return new Command(inputArr);
		}
	}

	public void parseAndSetData(String input, TaskList tasklist, Ui ui) {
		String inputLower = input.toLowerCase();
		if (inputLower.isBlank()) {
			return;
		}
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
				newTask = new Deadline(inputArr[2], parseDateTimeStr(inputArr[3], ui));
				break;

			case EVENT:
				newTask = new Event(inputArr[2],
						new LocalDateTime[] { parseDateTimeStr(inputArr[3], ui),
								parseDateTimeStr(inputArr[4], ui) });
				break;

			default:
				break;
		}
		if (inputArr[1].equals("1"))
			newTask.markAsDone();
		tasklist.add(newTask);
	}

	public LocalDateTime parseDateTimeStr(String dateTimeStr, Ui ui) {
		if (dateTimeStr.isBlank()) {
			return null;
		}
		String[] dateTimeArr = dateTimeStr.split(" ");
		LocalTime time = LocalTime.MIN;
		Integer[] dateArr;
		if (dateTimeArr.length > 1) {
			try {
				int hr = Integer.parseInt(dateTimeArr[1].substring(0, 2));
				int min = Integer.parseInt(dateTimeArr[1].substring(2, 4));
				time = LocalTime.of(hr, min);
			} catch (NumberFormatException ex) {
				ui.printError("Oops! Time format needs to be specified in proper form.");
				return null;
			}
		}

		try {
			if (dateTimeStr.contains("/")) { // Input Format: dd/MM/YYYY
				dateArr = Stream.of(dateTimeArr[0].split("/")).map(Integer::valueOf).toArray(Integer[]::new);
				return LocalDateTime.of(LocalDate.of(dateArr[2], dateArr[1], dateArr[0]), time);
			}

			// Input Format: YYYY-MM-dd
			dateArr = Stream.of(dateTimeArr[0].split("-")).map(Integer::valueOf).toArray(Integer[]::new);
			return LocalDateTime.of(LocalDate.of(dateArr[0], dateArr[1], dateArr[2]), time);
		} catch (ArrayIndexOutOfBoundsException ex) {
			ui.printError("Oops! Time format needs to be specified in proper form.");
			return null;
		}

	}

	public LocalDateTime getBy(String[] inputArr, Ui ui) {
		int l = inputArr.length;
		for (int i = 0; i < l; i++) {
			if (i < l - 1 && inputArr[i].equals("/by")) {
				return parseDateTimeStr(sliceArrAndConcate(inputArr, i + 1, l), ui);
			}
		}
		return null;
	}

	public LocalDateTime[] getFromTo(String[] inputArr, Ui ui) {
		int l = inputArr.length;
		int fromStartIdx = -1, fromEndIdx = l, toStartIdx = -1;
		LocalDateTime from = LocalDateTime.MIN;
		LocalDateTime to = LocalDateTime.MIN;
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
			from = parseDateTimeStr(sliceArrAndConcate(inputArr, fromStartIdx, fromEndIdx), ui);
		}
		if (toStartIdx > -1) {
			to = parseDateTimeStr(sliceArrAndConcate(inputArr, toStartIdx, l), ui);
		}
		return new LocalDateTime[] { from, to };
	}

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

	public String sliceArrAndConcate(String[] arr, int startIdx, int endIdx) {
		return String.join(" ", Arrays.copyOfRange(arr, startIdx, endIdx));
	}
}
