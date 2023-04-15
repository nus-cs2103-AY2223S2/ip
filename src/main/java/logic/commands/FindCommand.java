package logic.commands;

import exceptions.DukeException;
import model.TaskList;
import model.tasks.Todo;

public class FindCommand extends Command {
    private String[] command;

    public FindCommand(String[] command) {
        this.command = command;
    }

    public static void validate(String[] command) throws DukeException {
        assert command.length > 0 : "Command should not be empty";

        if (command.length == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    @Override
	public String execute(TaskList taskList) {
		String combinedString = String.join(" ", this.command);

		Todo newTodo = new Todo(combinedString);
		taskList.add(newTodo);
		return "Got it. I've added this task:" + combinedString + "\nNow you have " +
		 taskList.size() + " tasks in the list.";
	}
}
