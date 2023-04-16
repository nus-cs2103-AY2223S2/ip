package logic.commands;

import model.TaskList;
import model.tasks.Todo;
import exceptions.DukeException;
import logic.response.Response;

/**
 * Class representing the Todo Command
 */
public class TodoCommand extends Command {
	private String[] command;

	/**
	 * Constructor for TodoCommand
	 * @param command The command to be executed
	 */
	public TodoCommand(String[] command) {
		this.command = command;
	}

	/**
	 * Validates the Todo Command
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
	 * Executes the Todo Command
	 * @param taskList The TaskList to be modified
	 * @return The response to the Todo Command
	 */
	@Override
	public String execute(TaskList taskList) {
		String combinedString = String.join(" ", this.command);
		
		Todo newTodo = new Todo(combinedString);
		taskList.add(newTodo);
		return Response.getAddTaskResponse(newTodo, taskList);
	}
}
