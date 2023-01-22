package seedu.shao.commands;

import seedu.shao.task.Deadline;
import seedu.shao.task.Event;
import seedu.shao.task.Task;
import seedu.shao.task.Todo;

import java.time.LocalDateTime;

import seedu.shao.parser.Parser;
import seedu.shao.storage.Storage;
import seedu.shao.tasklist.TaskList;
import seedu.shao.ui.Ui;

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

	private void setTaskType(String message) {
		String commandLower = message.toLowerCase();
		this.taskType = commandLower.startsWith("todo")
				? TaskType.TODO
				: commandLower.startsWith("deadline")
						? TaskType.DEADLINE
						: TaskType.EVENT;
	}

	private void setTask(String[] inputArr, Ui ui, Parser parser) {
		String description = parser.sliceArrAndConcate(inputArr, 1, inputArr.length);
		switch (taskType) {
			case TODO:
				task = new Todo(description);
				break;

			case DEADLINE:
				LocalDateTime by = parser.getBy(inputArr, ui);
				if (by == null) {
					ui.printError("Oops! The description of deadline must include a completion date/time.");
					return;
				}
				task = new Deadline(parser.trimDate(description), by);
				break;

			case EVENT:
				LocalDateTime[] fromTo = parser.getFromTo(inputArr, ui);
				if (fromTo[0] == LocalDateTime.MIN) {
					ui.printError("Oops! The description of event must include a from date/time.");
					return;
				}
				if (fromTo[1] == LocalDateTime.MIN) {
					ui.printError("Oops! The description of event must include a to date/time.");
					return;
				}
				task = new Event(parser.trimDate(description), fromTo);
				break;

			default:
				break;
		}

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
			setTask(inputArr, ui, parser);
			tasklist.add(task);
			storage.saveNewData(task, ui);
			ui.printAddedTask(task, tasklist.size());
		} catch (NumberFormatException ex) {
			ui.printError("Oops! An item number must be provided.");
		}
	}

}
