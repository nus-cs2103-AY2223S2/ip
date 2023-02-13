package henz.command;

import henz.henzexception.CommandException;
import henz.storage.Storage;
import henz.tasklist.TaskList;
import henz.tasks.Task;
import henz.ui.Ui;

/**
 * EditDescriptionCommand class extends Command class.
 * This class represenst commands that edits task description
 */
public class EditDescriptionCommand extends Command {
    /**
     * Constructor.
     * @param request the request of the user.
     */
    public EditDescriptionCommand(String request) {
        super(request);
    }

    /**
     * Edits the tasks of the stated index.
     * @param tasks   the task list
     * @param ui      the ui instance
     * @param storage the storage instance
     * @return string
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        try {
            String result = "";
            String[] values = this.unwrap();
            String description = values[1];
            int index = Integer.parseInt(values[0]) - 1;

            Task task = tasks.get(index);
            result += task + " has been updated to: ";

            task.updateDescription(description);
            result += task;

            storage.save(tasks);
            return result;
        } catch (CommandException error) {
            return error.getMessage();

        }
    }

    /**
     * Checks if the command has the inputs that it needs for the creation of task.
     * @throws CommandException
     */
    @Override
    public void checkCommandRequirement() throws CommandException {
        String[] values = super.getRequest().split(" ");

        if (values.length < 3) {
            throw new CommandException("Description or Index cannot be empty");
        }

        String indexString = values[1];
        try {
            Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new CommandException("Index has to be a number");
        }
    }

    /**
     * Unwraps the user's command into a string array that holds the following
     * information:
     * <ol>
     * <li>Description - the description of the task</li>
     * </ol>
     * @return a string array that stores the index and description of
     *         the task.
     * @throws CommandException
     */
    @Override
    public String[] unwrap() throws CommandException {
        String request = super.getRequest();
        String[] values = request.split(" ");

        // Throws RequestExecution if there are any issues with the request
        checkCommandRequirement();

        String index = values[1];
        String description = values[2];

        for (int i = 3; i < values.length; i++) {
            description += " " + values[i];
        }

        return new String[] { index, description };
    }
}
