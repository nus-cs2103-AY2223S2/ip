package commands;

import exceptions.NoDateException;
import storage.Storage;
import storage.TaskList;
import tasks.Event;
import ui.Ui;

/**
 * This class is used to create a new Event task.
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructor for the EventCommand.
     * @param userInput The user input.
     */
    public EventCommand(String userInput) {
        assert userInput != null;
        this.description = getDescription(userInput);
        this.from = getFrom(userInput);
        this.to = getUntil(userInput);
    }

    /**
     * Returns the description to initialise the Event.
     * @param userInput The user input.
     * @return The description.
     */
    public String getDescription(String userInput) {
        return userInput.substring(6).split(" /from ")[0];
    }

    /**
     * Returns the starting date to initialise the Event.
     * @param userInput The user input.
     * @return The starting date.
     */
    public String getFrom(String userInput) {
        String[] temp = userInput.substring(6).split(" /from ")[1].split(" /to ");
        if (temp[0].trim().equals("")) {
            throw new NoDateException("event", null);
        }
        return temp[0];
    }

    /**
     * Returns the ending date to initialise the Event.
     * @param userInput The user input.
     * @return The ending date.
     */
    public String getUntil(String userInput) {
        String[] temp = userInput.substring(6).split(" /from ")[1].split(" /to ");
        if (temp.length == 1) {
            throw new NoDateException("event", null);
        }
        return temp[1];
    }

    /**
     * Create a new Event task and add it to the database.
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
        Event event = new Event(this.description, this.from, this.to);
        tasks.addTask(event);
        return ui.showAddTask(event, tasks.getSize());
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
