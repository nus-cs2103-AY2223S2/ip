package commands;

import exceptions.InvalidDateFormatException;
import storage.Storage;
import storage.TaskList;
import tasks.Deadline;
import ui.Ui;

/**
 * This class is used to create a new Deadline task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructor for the DeadlineCommand.
     * @param userInput The user input.
     */
    public DeadlineCommand(String userInput) {
        assert userInput != null;
        this.description = getDescription(userInput);
        this.by = getBy(userInput);
    }

    /**
     * Returns the description to initialise the Deadline.
     * @param userInput The user input.
     * @return The description.
     */
    public String getDescription(String userInput) {
        return userInput.substring(9).split(" /by ")[0];
    }

    /**
     * Returns the deadline to initialise the Deadline.
     * @param userInput The user input.
     * @return The deadline.
     */
    public String getBy(String userInput) {
        String[] temp = userInput.substring(9).split(" /by ");
        if (temp.length == 1) {
            throw new InvalidDateFormatException(null);
        }
        return temp[1];
    }

    /**
     * Create a new Deadline task and add it to the database.
     * @param tasks The database.
     * @param ui The user interface.
     * @param storage The storage.
     * @return String for executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        Deadline deadline = new Deadline(this.description, this.by);
        tasks.addTask(deadline);
        return ui.showAddTask(deadline, tasks.getSize());
    }

    /**
     * Check to continue the conversation.
     * @return True.
     */
    @Override
    public boolean isContinueConvo() {
        return true;
    }
}
