package duke.command;

import duke.task.Event;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.TextUi;

/**
 * Creates a event task and add it to the task list upon execution
 */

public class EventCommand extends Command {
    private final String DETAILS;

    /**
     * Constructs a EventCommand class with given parameter
     * @param details A string representation of type event task by user
     */
    public EventCommand(String details) {
        this.DETAILS = details;
    }

    /**
     * Break down the input parameter, creates Event task and add it to the task list
     * Generate ui message to user and saves the changes to file
     * @param tasksList A TaskList class that represents task list
     * @param ui A TextUi class that represents the ui
     * @param storage A Storage class which represents the storage of file
     */
    @Override
    public String execute(TaskList tasksList, TextUi ui, Storage storage) {
        String[] detailArray = DETAILS.split("/");
        String description = detailArray[0].strip();
        String from = detailArray[1].strip();
        String to = detailArray[2].strip();
        Task event = new Event(description, from, to);
        tasksList.addToTaskList(event);
        storage.saveToFile(tasksList.getList());
        return ui.showAddTaskMessage(event);
    }

    /**
     * Returns a boolean value to indicate whether to exit the program
     * @return a boolean value
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
