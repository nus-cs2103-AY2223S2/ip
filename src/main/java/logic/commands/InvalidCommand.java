package logic.commands;

import model.TaskList;

public class InvalidCommand extends Command{
	
	@Override
	public String execute(TaskList taskList) {
		return "Invalid command";
	}
}
