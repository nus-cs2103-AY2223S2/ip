package command;

import duke.DukeException;
import duke.Ui;
import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Command to set priority
 */
public class CommandPriority extends Command {

    private final TaskList taskList;
    private final String index;
    private final Storage storage;

    private final String priorityLevel;

    /**
     * Constructor for priority command.
     *
     * @param taskList List of all tasks.
     * @param index Index of task to mark, starting from 1.
     * @param storage Handles storage actions.
     */
    public CommandPriority(TaskList taskList, String index, Storage storage, String priorityLevel) {
        this.taskList = taskList;
        this.index = index;
        this.storage = storage;
        this.priorityLevel = priorityLevel;
    }

    @Override
    public String execute() throws DukeException {
        int i = this.getIndex(index);
        Task task = this.taskList.getTaskAt(i);
        String message = this.changePriority(task);
        this.updateFile();
        return message;
    }

    private int getIndex(String index) throws DukeException {
        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private String changePriority(Task task) throws DukeException {
        switch (this.priorityLevel) {
        case "h":
            return task.setPriorityHigh();
        case "m":
            return task.setPriorityMid();
        case "l":
            return task.setPriorityLow();
        default:
            throw new DukeException(Ui.getInvalidPriorityMessage());
        }
    }

    private void updateFile() throws DukeException {
        this.storage.overwriteFile(this.taskList);
    }


}
