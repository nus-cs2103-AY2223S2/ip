package logic.commands;

import logic.parser.Parser;
import logic.response.Response;
import model.TaskList;
import model.tasks.Task;

import exceptions.DukeException;

public class DeleteCommand extends Command{
	private String[] command;

	public DeleteCommand(String[] command) {
		this.command = command;
	}

	public static void validate(String[] command) throws DukeException {
		assert command.length > 0 : "Command should not be empty";

		if (command.length == 0) {
			throw new DukeException("The description of a todo cannot be empty.");
		}
	}

	@Override
	public String execute(TaskList taskList) {
		String strIndex = Parser.splitArray(this.command, " ").get(0);
		int index = Integer.parseInt(strIndex);
		Task task = taskList.delete(index);
		return Response.getDeleteTaskResponse(task, taskList);
	}
}
