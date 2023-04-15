package logic.commands;

import logic.response.Response;
import model.TaskList;

public class ListCommand extends Command{
	
	@Override
	public String execute(TaskList taskList) {
		return Response.getListResponse(taskList);
	}
}
