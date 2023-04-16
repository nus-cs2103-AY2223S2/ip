package logic.commands;

import exceptions.DukeException;
import exceptions.IllegalIndexException;
import logic.response.Response;
import model.TaskList;

/**
 * Class representing the Unmark Command
 */
public class UnmarkCommand extends Command{
	private String[] command;
	
	/**
	 * Constructor for UnmarkCommand
	 * @param command
	 */ 
	public UnmarkCommand(String[] command) {
		this.command = command;
	}

	/**
	 * Validates the Unmark Command
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
	 * Executes the Unmark Command
	 * @param taskList The TaskList to be modified
	 * @return The response to the Unmark Command
	 */
	@Override
	public String execute(TaskList taskList) {
		int index = Integer.parseInt(command[0]) - 1;
		taskList.markAsUndone(index);
		return Response.getUnmarkTaskResponse(taskList.get(index));
	}	
}
