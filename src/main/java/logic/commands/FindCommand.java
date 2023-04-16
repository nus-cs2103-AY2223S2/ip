package logic.commands;

import exceptions.DukeException;
import logic.response.Response;
import model.TaskList;

/**
 * Class representing the Find Command
 */
public class FindCommand extends Command {
    private String[] command;

    /**
     * Constructor for FindCommand
     * @param command The command to be executed
     */
    public FindCommand(String[] command) {
        this.command = command;
    }

    /**
     * Validates the Find Command
     * @param command The command to be validated
     * @throws DukeException
     */
    public static void validate(String[] command) throws DukeException {
        assert command.length > 0 : "Command should not be empty";

    }

    /**
     * Executes the Find Command
     * @param taskList The TaskList to be modified
     * @return The response to the Find Command
     */
    @Override
    public String execute(TaskList taskList) {
        String combinedString = String.join(" ", this.command);
        TaskList resTaskList = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            String taskDesc = taskList.get(i).getTaskDesc();
            if (taskDesc.contains(combinedString)) {
                resTaskList.add(taskList.get(i));
            }
        }
        return Response.getFindTaskResponse(resTaskList);
    }
}
