package logic.commands;

import model.TaskList;

public class ListCommand extends Command{
	
	@Override
	public String execute(TaskList taskList) {
		return taskList.toString();
	}
}
