package commands;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDateTime;

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

	private boolean setTask(String[] inputArr, Ui ui, Parser parser) {
		String description = parser.sliceArrAndConcate(inputArr, 1, inputArr.length);
		switch (taskType) {
			case TODO:
				task = new Todo(description);
				break;

			case DEADLINE:
				LocalDateTime by = parser.getBy(inputArr, ui);
				if (by == null) {
					ui.printError("Oops! The description of deadline must include a completion date/time.");
					return false;
				}
				task = new Deadline(parser.trimDate(description), by);
				break;

			case EVENT:
				LocalDateTime[] fromTo = parser.getFromTo(inputArr, ui);
				if (fromTo[0] == LocalDateTime.MIN) {
					ui.printError("The description of event must include a from date/time.");
					return false;
				}
				if (fromTo[1] == LocalDateTime.MIN) {
					ui.printError("The description of event must include a to date/time.");
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
		String[] inputArr = getMessageArray();
		if (inputArr.length < 2) {
			String errorMsg = String.format("Oops! The description of a %s cannot be empty.",
					this.taskType);
			ui.printError(errorMsg);
			return;
		}
		try {
			if (setTask(inputArr, ui, parser)) {
				tasklist.add(task);
				storage.saveNewData(task, ui);
				ui.printAddedTask(task, tasklist.size());
				return;
			}
		} catch (NumberFormatException ex) {
			ui.printError("Oops! An item number must be provided.");
		}
	}

}
