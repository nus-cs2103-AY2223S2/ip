package logic.commands;

import exceptions.DukeException;
import exceptions.IllegalIndexException;
import logic.response.Response;
import model.TaskList;

public class MarkCommand extends Command {
	private String[] command;

	public MarkCommand(String[] command) {
		this.command = command;
	}

	public static void validate(String[] command) throws DukeException {
		assert command.length > 0 : "Command should not be empty";
		
		if (command.length < 1) {
			throw new DukeException("Missing index!");
		}
		try {
			int index = Integer.parseInt(command[0]) - 1;
			if (index < 0) {
				throw new IllegalIndexException();
			}
		} catch (NumberFormatException e) {
				throw new DukeException(e.toString());
		}
	}

	@Override
	public String execute(TaskList taskList) {
		int index = Integer.parseInt(command[0]) - 1;
		taskList.markAsDone(index);
		return Response.getMarkTaskResponse(taskList.get(index));
	}
}
