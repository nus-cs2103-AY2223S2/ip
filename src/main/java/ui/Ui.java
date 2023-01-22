package ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import parser.Parser;
import task.Deadline;
import task.Event;
import task.Task;
import tasklist.TaskList;

public class Ui {

	private Scanner scan = new Scanner(System.in);

	enum TaskType {
		TODO, DEADLINE, EVENT
	}

	public void printError(String errorMessage) {
		println(errorMessage);
	}

	public void printRowDivider() {
		println("________________________________________________________");
	}

	public void println(String s) {
		System.out.println("\t" + s);
	}

	public void printAddedTask(Task task, int tasksCnt) {
		String msg1 = "Noted. I've added this task:";
		String msg2 = String.format("You have %d %s in your list currently.",
				tasksCnt, tasksCnt > 1 ? "tasks" : "task");

		println(msg1);
		println("  " + task.toString());
		println(msg2);
	}

	public void printMarkedTask(Task task, boolean isMark) {
		String body = isMark
				? "Nice! I've marked this task as done:"
				: "OK, I've marked this task as not done yet:";
		println(body);
		println(task.toString());
	}

	public void printItemDeleted(Task task, int tasksCnt) {
		println("Sure, I've removed this task:");
		println("  " + task.toString());
		println(String.format(
				"You have %d %s in your list currently.",
				tasksCnt, tasksCnt > 1 ? "tasks" : "task"));
	}

	public void printList(TaskList tasklist) {
		int numItems = tasklist.size();
		String header = numItems == 0 ? "There are no tasks in your list. Please add one."
				: "Here are the tasks in your list: ";

		println(header);
		for (int i = 0; i < numItems; i++) {
			Task curTask = tasklist.get(i);
			println(String.format("%d.%s", i + 1, curTask));
		}
	}

	public void printDeadlineEventOnDatetime(TaskList tasklist, String dateTimeStr, Parser parser) {
		boolean hasItem = false;
		LocalDateTime dateTime = parser.parseDateTimeStr(dateTimeStr, this);
		String dtStrOutput = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));

		int itemNum = 1;
		for (int i = 0; i < tasklist.size(); i++) {
			Task curTask = tasklist.get(i);
			if (curTask instanceof Deadline) {
				Deadline deadline = (Deadline) curTask;
				if (deadline.getBy().equals(dateTime)) {
					if (!hasItem) {
						println(String.format(
								"These are the deadline/events that occur on %s", dtStrOutput));
					}
					hasItem = true;
					println(String.format("%d.%s", itemNum, deadline));
					itemNum += 1;
				}
			}
			if (curTask instanceof Event) {
				Event event = (Event) curTask;
				if (event.getFrom().equals(dateTime) || event.getTo().equals(dateTime)) {
					if (!hasItem) {
						println(String.format(
								"These are the deadline/events that occur on %s", dtStrOutput));
					}
					hasItem = true;
					println(String.format("%d.%s", itemNum, event));
					itemNum += 1;
				}
			}
		}
		if (!hasItem) {
			println(String.format(
					"No deadline/events occur on %s", dtStrOutput));
		}
	}

	public void greetUser() {
		println("\tHi There! I'm Shao");
		println("\tWhat can I do for you?");
	}

	public String readCommand() {
		if (!scan.hasNextLine()) {
			return "";
		}
		String input = scan.nextLine().trim();
		return input;
	}

	public void cleanUp() {
		scan.close();
	}

	// public void readInput(TaskList tasklist, Storage storage, Parser parser) {
	// Scanner scan = new Scanner(System.in);

	// while (scan.hasNextLine()) {
	// String input = scan.nextLine().trim();
	// String inputLower = input.toLowerCase();
	// if (inputLower.isBlank())
	// continue;
	// switch (inputLower) {
	// case "bye":
	// exitUser();
	// return;
	// case "list":
	// printList(tasklist);
	// break;
	// default:
	// boolean isDateTimeOperation = inputLower.startsWith("datetime");
	// boolean isDeleteOperation = inputLower.startsWith("delete");
	// boolean isMarkOperation = inputLower.startsWith("mark")
	// || inputLower.startsWith("unmark");
	// String[] inputArr = input.split(" ");
	// if (isDateTimeOperation) {
	// if (inputArr.length < 2) {
	// printError("Oops! Datetime cannot be empty.");
	// } else {
	// printDeadlineEventOnDatetime(tasklist,
	// parser.sliceArrAndConcate(inputArr, 1, inputArr.length), parser);
	// }
	// } else if (isDeleteOperation || isMarkOperation) {
	// if (inputArr.length < 2) {
	// printError("Oops! The item number cannot be empty.");
	// } else {
	// try {
	// if (isDeleteOperation) {
	// tasklist.deleteItem(inputArr[1], storage, this);
	// } else {
	// tasklist.markItem(inputArr[1], inputLower.startsWith("mark"), storage, this);
	// }
	// } catch (NumberFormatException ex) {
	// printError("Oops! An item number must be provided.");
	// }
	// }
	// } else if (inputLower.startsWith("todo") || inputLower.startsWith("deadline")
	// || inputLower.startsWith("event")) {

	// TaskType operationType = inputLower.startsWith("todo")
	// ? TaskType.TODO
	// : inputLower.startsWith("deadline")
	// ? TaskType.DEADLINE
	// : TaskType.EVENT;

	// if (inputArr.length < 2) {
	// printError(String.format("Oops! The description of a %s cannot be empty.",
	// operationType == TaskType.TODO ? "todo"
	// : operationType == TaskType.DEADLINE ? "deadline" : "event"));
	// } else {
	// Task newTask = null;
	// String description = parser.sliceArrAndConcate(inputArr, 1, inputArr.length);
	// switch (operationType) {
	// case TODO:
	// newTask = new Todo(description);
	// break;

	// case DEADLINE:
	// LocalDateTime by = parser.getBy(inputArr, this);
	// if (by == null) {
	// printError(
	// "Oops! The description of deadline must include a completion date/time.");
	// continue;
	// }
	// newTask = new Deadline(parser.trimDate(description), by);
	// break;

	// case EVENT:
	// LocalDateTime[] fromTo = parser.getFromTo(inputArr, this);
	// if (fromTo[0] == LocalDateTime.MIN) {
	// printError("Oops! The description of event must include a from date/time.");
	// continue;
	// }
	// if (fromTo[1] == LocalDateTime.MIN) {
	// printError("Oops! The description of event must include a to date/time.");
	// continue;
	// }
	// newTask = new Event(parser.trimDate(description), fromTo);
	// break;

	// default:
	// break;
	// }
	// tasklist.add(newTask);
	// storage.saveNewData(newTask);
	// printAddedTask(newTask, tasklist.size());
	// }
	// } else {
	// printError("Oops! I'm sorry but I don't know what that means.");
	// }
	// break;

	// }
	// }

	// scan.close();
	// }

}
