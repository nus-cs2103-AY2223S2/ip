package logic.commands;

import logic.response.Response;
import model.TaskList;

/**
 * Class representing the List Command
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand
     */
    @Override
    public String execute(TaskList taskList) {
        return Response.getListResponse(taskList);
    }
}
