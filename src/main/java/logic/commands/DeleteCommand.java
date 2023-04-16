package logic.commands;

import logic.parser.Parser;
import logic.response.Response;
import model.TaskList;
import model.tasks.Task;

import exceptions.DukeException;

/**
 * Class representing the Delete Command
 */
public class DeleteCommand extends Command {
    private String[] command;

    /**
     * Constructor for DeleteCommand
     * @param command The command to be executed
     */
    public DeleteCommand(String[] command) {
        this.command = command;
    }

    /**
     * Validates the Delete Command
     * @param command The command to be validated
     * @throws DukeException
     */
    public static void validate(String[] command) throws DukeException {
        assert command.length > 0 : "Command should not be empty";

        if (command.length == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Executes the Delete Command
     * @param taskList The TaskList to be modified
     * @return The response to the Delete Command
     */
    @Override
    public String execute(TaskList taskList) {
        String strIndex = Parser.splitArray(this.command, " ").get(0);
        int index = Integer.parseInt(strIndex) - 1;
        Task task = taskList.delete(index);
        return Response.getDeleteTaskResponse(task, taskList);
    }
}
