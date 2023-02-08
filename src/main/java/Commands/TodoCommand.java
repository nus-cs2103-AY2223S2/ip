package commands;

import storage.Storage;
import storage.TaskList;
import tasks.ToDo;
import ui.Ui;

/**
 * This class is used to create a new To Do task.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Constructor for the ToDoCommand.
     * @param userInput The user input.
     */
    public TodoCommand(String userInput) {
        assert userInput != null;
        this.description = getDescription(userInput);
    }

    /**
     * Returns the description to initialise the To Do.
     * @param userInput The user input.
     * @return The description.
     */
    public String getDescription(String userInput) {
        return userInput.substring(5);
    }

    /**
     * Create a new To Do task and add it to the database.
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
        ToDo toDo = new ToDo(this.description);
        tasks.addTask(toDo);
        return ui.showAddTask(toDo, tasks.getSize());
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
