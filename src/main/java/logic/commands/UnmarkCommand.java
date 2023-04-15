package logic.commands;

import exceptions.DukeException;
import exceptions.IllegalIndexException;
import model.TaskList;

public class UnmarkCommand extends Command{
	private String[] command;
	
	public UnmarkCommand(String[] command) {
		this.command = command;
	}

	public static void validate(String[] command) throws DukeException {
		assert command.length > 0 : "Command should not be empty";
		
		if (command.length < 1) {
			throw new DukeException("Missing index!");
		}
		try {
			int index = Integer.parseInt(command[0]) - 1;
			if (index < 0) {
				throw new IllegalIndexException();
			}
		} catch (NumberFormatException e) {
			throw new DukeException(e.toString());
		}
	}
	
	@Override
	public String execute(TaskList taskList) {
		int index = Integer.parseInt(command[0]) - 1;
		taskList.markAsUndone(index);
		return "Nice! I've marked this task as done: " + taskList.get(index);
	}	
}
