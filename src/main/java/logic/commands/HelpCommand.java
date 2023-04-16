package logic.commands;

import logic.response.Response;
import model.TaskList;

/**
 * Class representing the Help Command
 */
public class HelpCommand extends Command {
	
	/**
	 * Constructor for HelpCommand
	 */
	@Override
	public String execute(TaskList taskList) {
		return Response.getHelpResponse();
	}
}
