package logic.commands;

import logic.response.Response;
import model.TaskList;
import logic.response.Response;

public class ByeCommand extends Command {
  
	public ByeCommand() {
		super();
	}
	
	@Override
	public String execute(TaskList taskList) {
		return Response.getExitResponse();
	}

	@Override
	public boolean isExit() {
		this.isExit = true;
		return this.isExit;
	}
}
