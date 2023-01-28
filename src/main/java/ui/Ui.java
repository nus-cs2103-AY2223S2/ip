package ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import parser.Parser;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import tasklist.TaskList;

public class Ui {

	final private int BOX_PADDING = 20;

	private Scanner scan = new Scanner(System.in);

	/**
	 * Print a line of error message.
	 * 
	 * @param errorMessage
	 */
	public void printError(String errorMessage) {
		println(errorMessage);
	}

	/** Print a row separator line. */
	public void printRowDivider() {
		println("________________________________________________________");
	}

	/**
	 * Print line of a provided string.
	 * 
	 * @param s
	 */
	public void println(String s) {
		System.out.println("\t" + s);
	}

	/**
	 * Print a response when a new task is added.
	 * 
	 * @param task
	 * @param tasksCnt
	 * @param dialogContainer
	 * @param storage
	 */
	public void printAddedTask(Task task, int tasksCnt, VBox dialogContainer, Storage storage) {
		String msg1 = "Noted. I've added this task:";
		String msg2 = String.format("You have %d %s in your list currently.",
				tasksCnt, tasksCnt > 1 ? "tasks" : "task");
		String taskItem = "  " + task.toString();

		VBox messages = new VBox();
		messages.getChildren().addAll(createLabel(msg1), createLabel(taskItem), createLabel(msg2));
		sendResponse(dialogContainer, storage, messages);
	}

	public Label createLabel(String message) {
		Label label = new Label(message);
		label.setWrapText(true);
		return label;
	}

	/**
	 * Print a response when a new task is marked or unmarked.
	 * 
	 * @param task
	 * @param isMark
	 */
	public void printMarkedTask(Task task, boolean isMark, Storage storage, VBox dialogContainer) {
		String body = isMark
				? "Nice! I've marked this task as done:"
				: "OK, I've marked this task as not done yet:";
		VBox messages = new VBox();
		messages.getChildren().addAll(createLabel(body), createLabel(task.toString()));
		sendResponse(dialogContainer, storage, messages);
	}

	/**
	 * Print a response when an existing task is deleted.
	 * 
	 * @param task
	 * @param tasksCnt
	 * @param dialogContainer
	 * @param storage
	 */
	public void printDeletedTask(Task task, int tasksCnt, VBox dialogContainer, Storage storage) {
		String header = "Sure, I've removed this task:";
		String taskItem = "  " + task.toString();
		String body = String.format(
				"You have %d %s in your list currently.",
				tasksCnt, tasksCnt > 1 ? "tasks" : "task");
		VBox messages = new VBox();
		messages.getChildren().addAll(createLabel(header), createLabel(taskItem), createLabel(body));
		sendResponse(dialogContainer, storage, messages);
	}

	/**
	 * Print the set of recorded tasks.
	 * 
	 * @param tasklist
	 */
	public void printList(TaskList tasklist, Storage storage, VBox dialogContainer) {
		int numItems = tasklist.size();
		String header = numItems == 0 ? "There are no tasks in your list. Please add one."
				: "Here are the tasks in your list: ";

		VBox messages = new VBox();
		messages.getChildren().add(createLabel(header));

		for (int i = 0; i < numItems; i++) {
			Task curTask = tasklist.get(i);
			Label curTaskDetails = createLabel(String.format("%d. %s", i + 1, curTask));
			messages.getChildren().add(curTaskDetails);
		}
		sendResponse(dialogContainer, storage, messages);
	}

	/**
	 * Print the events and deadlines which falls on the specified datetime.
	 * 
	 * @param tasklist
	 * @param dateTimeStr
	 * @param parser
	 */
	public void printDeadlineEventOnDatetime(TaskList tasklist, Storage storage,
			Parser parser, VBox dialogContainer, String dateTimeStr) {
		boolean hasItem = false;
		LocalDateTime dateTime = parser.parseDateTimeStr(dateTimeStr, this, storage, dialogContainer);
		String dtStrOutput = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
		String header = String.format("These are the deadline/events that occur on %s", dtStrOutput);
		VBox messages = new VBox();

		int itemNum = 1;
		for (int i = 0; i < tasklist.size(); i++) {
			Task curTask = tasklist.get(i);
			if (curTask instanceof Deadline) {
				Deadline deadline = (Deadline) curTask;
				if (deadline.getBy().equals(dateTime)) {
					if (!hasItem) {
						messages.getChildren().add(createLabel(header));
					}
					hasItem = true;
					messages.getChildren().add(
							createLabel(String.format("%d. %s", itemNum, deadline)));
					itemNum += 1;
				}
			}
			if (curTask instanceof Event) {
				Event event = (Event) curTask;
				if (event.getFrom().equals(dateTime) || event.getTo().equals(dateTime)) {
					if (!hasItem) {
						messages.getChildren().add(createLabel(header));
					}
					hasItem = true;
					messages.getChildren().add(
							createLabel(String.format("%d. %s", itemNum, event)));
					itemNum += 1;
				}
			}
		}
		if (!hasItem) {
			header = String.format("No deadline/events occur on %s", dtStrOutput);
			sendResponse(dialogContainer, storage, createLabel(header));
			return;
		}
		sendResponse(dialogContainer, storage, messages);
	}

	/**
	 * Print the tasks that matches the search keyword.
	 * 
	 * @param tasklist
	 * @param keyword
	 * @param dialogContainer
	 * @param storage
	 */
	public void printMatchedTasks(TaskList tasklist, Storage storage, VBox dialogContainer, String keyword) {
		boolean hasItem = false;
		String header = "Here are the matching tasks in your list:";
		VBox messages = new VBox();

		int itemNum = 1;
		for (int i = 0; i < tasklist.size(); i++) {
			Task curTask = tasklist.get(i);
			if (curTask.getDescription().contains(keyword)) {
				if (!hasItem) {
					messages.getChildren().add(createLabel(header));
				}
				hasItem = true;
				messages.getChildren().add(
						createLabel(String.format("%d. %s", itemNum, curTask)));
				itemNum += 1;
			}
		}
		if (!hasItem) {
			header = "There are no matching tasks.";
			sendResponse(dialogContainer, storage, createLabel(header));
			return;
		}
		sendResponse(dialogContainer, storage, messages);
	}

	public void greetUser(VBox dialogContainer, Storage storage) {
		String s1 = "Hi There! I'm Shao";
		String s2 = "What can I do for you?";
		VBox messages = new VBox();
		messages.getChildren().addAll(createLabel(s1), createLabel(s2));
		sendResponse(dialogContainer, storage, messages);
	}

	public void sendResponse(VBox dialogContainer, Storage storage, Node messages) {
		HBox rowContainer = new HBox();
		rowContainer.setAlignment(Pos.CENTER_LEFT);
		rowContainer.setPadding(new Insets(BOX_PADDING));
		rowContainer.setSpacing(10);
		rowContainer.getChildren().addAll(storage.getBotImageView(), messages);
		dialogContainer.getChildren().add(rowContainer);
	}

	public void sendInput(VBox dialogContainer, Storage storage, String message) {
		HBox rowContainer = new HBox();
		rowContainer.setAlignment(Pos.CENTER_RIGHT);
		rowContainer.setPadding(new Insets(BOX_PADDING));
		rowContainer.setSpacing(10);
		rowContainer.getChildren().addAll(createLabel(message), storage.getUserImageView());
		dialogContainer.getChildren().add(rowContainer);
	}

	/**
	 * Receive user input
	 * 
	 * @return String
	 */
	public String readCommand() {
		if (!scan.hasNextLine()) {
			return "";
		}
		String input = scan.nextLine().trim();
		return input;
	}

	/** Operations performed before program terminates */
	public void cleanUp() {
		scan.close();
	}

}
