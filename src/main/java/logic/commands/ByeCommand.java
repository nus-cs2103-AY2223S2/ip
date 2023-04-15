package logic.commands;

import model.TaskList;

public class ByeCommand extends Command {
  
	public ByeCommand() {
		super();
	}
	
	@Override
	public String execute(TaskList taskList) {
		return "Bye. Hope to see you again soon!";
	}

	@Override
	public boolean isExit() {
		this.isExit = true;
		return this.isExit;
	}
}
