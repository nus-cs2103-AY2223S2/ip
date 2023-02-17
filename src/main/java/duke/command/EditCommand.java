package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Command to edit the task information.
 */
public class EditCommand extends Command {
    private String partToEdit;
    private String taskNum;
    private String content;

    /**
     * Constructor for EditCommand class.
     * @param taskNum
     * @param partToBeEdited
     * @param content
     */
    public EditCommand(String taskNum, String partToBeEdited, String content) {
        this.partToEdit = partToBeEdited;
        this.taskNum = taskNum;
        this.content = content;
    }

    /**
     * Checks all the relevant fields if they are of the correct format.
     * @param taskNum
     * @param partToEdited
     * @param content
     * @param tasks
     * @param ui
     * @return
     */
    public String checkValidity(String taskNum, String partToEdited, String content, TaskList tasks, Ui ui) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNum) - 1;
        } catch (NumberFormatException e) {
            return ui.showInvalidCommandMsg();
        }
        if (isNotExistTask(taskNumber, tasks)) {
            return ui.showNonExistentTask(tasks.getLength());
        }
        if (isNotAvailablePart(partToEdited) || isBlankContent(content)) {
            return ui.showInvalidCommandMsg();
        }
        return "";
    }

    /**
     * Returns true if the task do not exist in the task list and false if it exist.
     * @param taskNum
     * @param tasks
     * @return
     */
    public boolean isNotExistTask(int taskNum, TaskList tasks) {
        if (taskNum >= tasks.getLength()) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the part the user want to edit is not valid and false if it is valid.
     * @param partToEdit
     * @return
     */
    public boolean isNotAvailablePart(String partToEdit) {
        if (partToEdit.isBlank()) {
            return true;
        } else if (partToEdit.equals("desc")) {
            return false;
        } else if (partToEdit.equals("by")) {
            return false;
        } else if (partToEdit.equals("from")) {
            return false;
        } else if (partToEdit.equals("to")) {
            return true;
        } else {
            return true;
        }
    }

    /**
     * Returns true if the content that the users want to change is blank and false if it is not blank.
     * @param content
     * @return
     */
    public boolean isBlankContent(String content) {
        if (content.isBlank()) {
            return true;
        }
        return false;
    }

    /**
     * Edits the task information requested by the user.
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String str = checkValidity(taskNum, partToEdit, content, tasks, ui);
        if (!str.isBlank()) {
            return str;
        }

        int taskNumber = Integer.parseInt(taskNum) - 1;
        Task taskToBeEdited = tasks.getTask(taskNumber);
        if (taskToBeEdited.getTaskType().equals("T")) {
            Todo task = (Todo) taskToBeEdited;
            task.editInfo(content);
            tasks.set(taskNumber, task);
        } else if (taskToBeEdited.getTaskType().equals("D")) {
            Deadline task = (Deadline) taskToBeEdited;
            task.editInfo(partToEdit, content);
            tasks.set(taskNumber, task);
        } else {
            Event task = (Event) taskToBeEdited;
            task.editInfo(partToEdit, content);
            tasks.set(taskNumber, task);
        }
        return ui.showEditedMsg(taskToBeEdited);
    }


    /**
     * Checks if this command will result in termination of duke.
     *
     * @return whether the program is exited.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
