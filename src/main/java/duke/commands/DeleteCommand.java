package duke.commands;

import java.util.ArrayList;

import duke.components.Storage;
import duke.components.TaskList;
import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * This is the DeleteCommand class to represent delete commands passed to Duke.
 * Encapsulates the information needed to remove a task from the TaskList.
 */
public class DeleteCommand extends Command {
    static final int COMMANDLENGTH = 2;
    static final int INDEXOFINDEX = 1;

    private Task taskToBeDeleted;
    private int indexOfTask;
    /**
     * public constructor for a new DeleteCommand
     * @param tokens arraylist representation of a tokenized user command.
     * @throws DukeException when input is invalid
     */
    public DeleteCommand(ArrayList<String> tokens) throws DukeException {
        super(tokens);
        if (tokens.size() != COMMANDLENGTH) {
            throw new DukeException(
                    "Invalid input received!"
                    + "\nDelete commands are in the form of: delete i "
                    + "\n(only 1 whitespace allowed)"
            );
        }
        this.indexOfTask = Integer.parseInt(tokens.get(INDEXOFINDEX));
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (indexOfTask < 1 || indexOfTask > tasks.size()) {
            throw new DukeException(
                    "index " + indexOfTask + " not in range of tasklist!");
        }
        this.taskToBeDeleted = tasks.getTask(indexOfTask);
        tasks.deleteTask(indexOfTask);
        return "Noted. I've removed this task:\n"
                + this.taskToBeDeleted
                + "\nNow you have "
                + tasks.size()
                + " tasks in the list.";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
