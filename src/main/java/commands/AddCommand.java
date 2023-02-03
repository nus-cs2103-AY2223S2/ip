package commands;

import java.time.LocalDateTime;

import javafx.scene.layout.VBox;
import parser.Parser;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import tasklist.TaskList;
import ui.Ui;

public class AddCommand extends Command {

	enum TaskType {
		TODO, DEADLINE, EVENT
	}

	private Task task;
	private TaskType taskType;

	public AddCommand(String[] messageArray) {
		super(messageArray);
		setTaskType(messageArray[0]);
	}

	public TaskType getTaskType() {
		return this.taskType;
	}

	/**
	 * Set task type from user input.
	 * 
	 * @param message User input text.
	 */
	private void setTaskType(String message) {
		String commandLower = message.toLowerCase();
		this.taskType = commandLower.startsWith("todo")
				? TaskType.TODO
				: commandLower.startsWith("deadline")
						? TaskType.DEADLINE
						: TaskType.EVENT;
	}

	/**
	 * Set task from task type.
	 * 
	 * @param inputArr        An array of strings from user text input.
	 * @param ui              A service to render the page of GUI.
	 * @param parser          A service to parse text input.
	 * @param storage         A store that represents the data access object (DAO).
	 * @param dialogContainer A container that holds all the rows of labels.
	 * @param tasklist        All the tasks that are recorded.
	 * @return boolean
	 */
	private boolean setTask(String[] inputArr, Ui ui, Parser parser, Storage storage, VBox dialogContainer,
			TaskList tasklist) {
		String description = parser.sliceArrAndConcate(inputArr, 1, inputArr.length);
		String errorMessage = String.format(
				"Oops! The description of a %s ", this.taskType);
		String duplicatedErrorMessage = String.format(
				"Oops! This %s has already been recorded.", taskType);

		switch (taskType) {
			case TODO:
				if (tasklist.hasDuplicateTodo(description)) {
					ui.sendResponse(dialogContainer, storage, ui.createLabel(duplicatedErrorMessage));
					return false;
				}
				task = new Todo(description);
				break;
			case DEADLINE:
				description = parser.trimDate(description);
				LocalDateTime by = parser.getBy(inputArr, ui, storage, dialogContainer);
				if (by == null) {
					return false;
				}
				if (tasklist.hasDuplicateDeadline(description, by)) {
					ui.sendResponse(dialogContainer, storage, ui.createLabel(duplicatedErrorMessage));
					return false;
				}
				if (by.isBefore(LocalDateTime.now())) {
					errorMessage = "Oops! BY datetime must be after the current datetime";
					ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
					return false;
				}
				task = new Deadline(description, by);
				break;
			case EVENT:
				description = parser.trimDate(description);
				LocalDateTime[] fromTo = parser.getFromTo(inputArr, ui, storage, dialogContainer);
				LocalDateTime from = fromTo[0];
				LocalDateTime to = fromTo[1];
				if (from == null && to == null) {
					return false;
				}
				if (from == null) {
					errorMessage += "must include a FROM datetime.";
					ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
					return false;
				}
				if (to == null) {
					errorMessage += "must include a TO datetime.";
					ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
					return false;
				}
				if (tasklist.hasDuplicateEvent(description, from, to)) {
					errorMessage = duplicatedErrorMessage;
					ui.sendResponse(dialogContainer, storage, ui.createLabel(duplicatedErrorMessage));
					return false;
				}
				if (from.isBefore(LocalDateTime.now())) {
					errorMessage = "Oops! FROM datetime must be after the current datetime";
					ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
					return false;
				}
				if (from.isAfter(to)) {
					errorMessage = "Oops! FROM datetime should be later than TO date/time.";
					ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
					return false;
				}
				task = new Event(description, fromTo);
				break;
			default:
				return false;
		}
		return true;

	}

	@Override
	public void execute(Object... args) {
		Ui ui = (Ui) args[0];
		Parser parser = (Parser) args[1];
		Storage storage = (Storage) args[2];
		TaskList tasklist = (TaskList) args[3];
		VBox dialogContainer = (VBox) args[4];
		String[] inputArr = getMessageArray();
		if (inputArr.length < 2) {
			String errorMessage = String.format(
					"Oops! The description of a %s cannot be empty.", this.taskType);
			ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
			return;
		}
		if (setTask(inputArr, ui, parser, storage, dialogContainer, tasklist)) {
			tasklist.add(task);
			storage.saveNewData(task, ui);
			ui.printAddedTask(task, tasklist.size(), dialogContainer, storage);
		}
	}

}
