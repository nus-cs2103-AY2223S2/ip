package logic.commands;

import logic.response.Response;
import model.TaskList;

/**
 * Class representing an Invalid Command
 */
public class InvalidCommand extends Command {

    /**
     * Constructor for InvalidCommand
     */
    @Override
    public String execute(TaskList taskList) {
        return Response.getInvalidCommandResponse();
    }
}
