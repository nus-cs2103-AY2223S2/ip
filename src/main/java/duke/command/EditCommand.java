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
     * Edit the task information requested by the user.
     * @param tasks
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
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
     * Check if this command will result in termination of duke.
     *
     * @return whether the program is exited.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
