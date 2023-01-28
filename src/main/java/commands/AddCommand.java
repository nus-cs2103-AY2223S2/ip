package commands;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDateTime;

import javafx.scene.layout.VBox;
import parser.Parser;
import storage.Storage;
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

	private void setTaskType(String message) {
		String commandLower = message.toLowerCase();
		this.taskType = commandLower.startsWith("todo")
				? TaskType.TODO
				: commandLower.startsWith("deadline")
						? TaskType.DEADLINE
						: TaskType.EVENT;
	}

	private boolean setTask(String[] inputArr, Ui ui, Parser parser, Storage storage, VBox dialogContainer) {
		String description = parser.sliceArrAndConcate(inputArr, 1, inputArr.length);
		String errorMessage;
		switch (taskType) {
			case TODO:
				task = new Todo(description);
				break;

			case DEADLINE:
				LocalDateTime by = parser.getBy(inputArr, ui, storage, dialogContainer);
				if (by == null) {
					errorMessage = "Oops! The description of DEADLINE must include a BY date/time.";
					ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
					return false;
				}
				task = new Deadline(parser.trimDate(description), by);
				break;

			case EVENT:
				LocalDateTime[] fromTo = parser.getFromTo(inputArr, ui, storage, dialogContainer);
				if (fromTo[0] == LocalDateTime.MIN) {
					errorMessage = "Oops! The description of EVENT must include a FROM date/time.";
					ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
					return false;
				}
				if (fromTo[1] == LocalDateTime.MIN) {
					errorMessage = "Oops! The description of EVENT must include a TO date/time.";
					ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
					return false;
				}

				task = new Event(parser.trimDate(description), fromTo);
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
		String errorMessage;
		if (inputArr.length < 2) {
			errorMessage = String.format(
					"Oops! The description of a %s cannot be empty.", this.taskType);
			ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
			return;
		}
		try {
			if (setTask(inputArr, ui, parser, storage, dialogContainer)) {
				tasklist.add(task);
				storage.saveNewData(task, ui);
				ui.printAddedTask(task, tasklist.size(), dialogContainer, storage);
				return;
			}
		} catch (NumberFormatException ex) {
			errorMessage = "Oops! The description of DEADLINE must include a BY datetime.";
			ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
		}
	}

}
