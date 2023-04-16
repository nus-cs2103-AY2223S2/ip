package logic.commands;

import logic.response.Response;
import model.TaskList;

public class ByeCommand extends Command {
  
	public ByeCommand() {
		super();
	}
	
	@Override
	public String execute(TaskList taskList) {
		this.isExit = true;
		return Response.getExitResponse();
	}
}
