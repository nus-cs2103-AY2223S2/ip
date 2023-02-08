package commands;

import exceptions.InvalidAttributeException;
import exceptions.InvalidInputException;
import parser.Parser;
import storage.Storage;
import storage.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import ui.Ui;

/**
 * This class helps to update details of a task.
 */
public class UpdateCommand extends Command {
    private int taskNumber;
    private String detailToEdit;
    private String detail;

    /**
     * Constructor for the UpdateCommand.
     * @param userInput The user input.
     */
    public UpdateCommand(String userInput) {
        assert userInput != null;
        this.taskNumber = getTaskNumber(userInput);
        this.detailToEdit = getDetailToEdit(userInput);
        this.detail = getNewDetail(userInput);
    }

    /**
     * Returns the task number from the database.
     * @param userInput The user input.
     * @return The task number from the database.
     */
    private int getTaskNumber(String userInput) {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
        return taskNumber;
    }

    /**
     * Returns which detail of the task to edit.
     * @param userInput The user input.
     * @return The detail to edit.
     */
    public String getDetailToEdit(String userInput) {
        return userInput.split("/")[1].split(" ")[0];
    }

    /**
     * Returns the new details of the task after edit.
     * @param userInput The user input.
     * @return The new details.
     */
    public String getNewDetail(String userInput) {
        String[] temp = userInput.split("/")[1].split(" ");
        String output = "";
        for (int i = 1; i < temp.length; i++) {
            output += temp[i] + " ";
        }
        return output.trim();
    }

    /**
     * Updates the respective detail of the task.
     * @param task The task to update.
     */
    private void updateTask(Task task) {
        switch(this.detailToEdit.toLowerCase()) {
        case "description":
            task.setDescription(this.detail);
            break;
        case "by":
            if (task instanceof Deadline) {
                Deadline temp = (Deadline) task;
                temp.setBy(Parser.parseDate(this.detail));
            } else {
                throw new InvalidAttributeException("deadline", null);
            }
            break;
        case "from":
            if (task instanceof Event) {
                Event temp = (Event) task;
                temp.setFrom(Parser.parseDate(this.detail));
            } else {
                throw new InvalidAttributeException("event", null);
            }
            break;
        case "to":
            if (task instanceof Event) {
                Event temp = (Event) task;
                temp.setUntil(Parser.parseDate(this.detail));
            } else {
                throw new InvalidAttributeException("event", null);
            }
            break;
        default:
            throw new InvalidInputException(null);
        }
    }

    /**
     * Retrieves and updates the respective detail of the task.
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
        Task task = tasks.getTaskByIndex(this.taskNumber - 1);
        updateTask(task);
        return ui.showUpdatedTask(task);
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
