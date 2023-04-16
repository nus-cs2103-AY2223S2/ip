package logic.commands;

import exceptions.DukeException;
import exceptions.IllegalIndexException;
import logic.response.Response;
import model.TaskList;

/**
 * Class representing the Mark Command
 */
public class MarkCommand extends Command {
	private String[] command;

	/**
	 * Constructor for MarkCommand
	 * @param command
	 */
	public MarkCommand(String[] command) {
		this.command = command;
	}

	/**
	 * Validates the Mark Command
	 * @param command The command to be validated
	 * @throws DukeException
	 */
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

	/**
	 * Executes the Mark Command
	 * @param taskList The TaskList to be modified
	 * @return The response to the Mark Command
	 */
	@Override
	public String execute(TaskList taskList) {
		int index = Integer.parseInt(command[0]) - 1;
		taskList.markAsDone(index);
		return Response.getMarkTaskResponse(taskList.get(index));
	}
}
