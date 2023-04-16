package logic.commands;

import exceptions.DukeException;
import logic.response.Response;
import model.TaskList;

public class FindCommand extends Command {
    private String[] command;

    public FindCommand(String[] command) {
        this.command = command;
    }

    public static void validate(String[] command) throws DukeException {
        assert command.length > 0 : "Command should not be empty";

    }

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
