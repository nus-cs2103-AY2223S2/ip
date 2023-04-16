package logic.commands;

import logic.response.Response;
import model.TaskList;

/**
 * Class representing the Bye Command
 */
public class ByeCommand extends Command {

	/**
	 * Constructor for ByeCommand
	 */
	public ByeCommand() {
		super();
	}
	
	/**
	 * Executes the Bye Command
	 * @param taskList The TaskList to be modified
	 * @return The response to the Bye Command
	 */
	@Override
	public String execute(TaskList taskList) {
		this.isExit = true;
		return Response.getExitResponse();
	}
}
