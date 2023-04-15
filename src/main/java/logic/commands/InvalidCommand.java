package logic.commands;

import logic.response.Response;
import model.TaskList;

public class InvalidCommand extends Command{
	
	@Override
	public String execute(TaskList taskList) {
		return Response.getInvalidCommandResponse();
	}
}
