package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

public class EditCommand extends Command {
    private String partToEdit;
    private String taskNum;
    private String content;

    public EditCommand(String taskNum, String partToBeEdited, String content) {
        this.partToEdit = partToBeEdited;
        this.taskNum = taskNum;
        this.content = content;
    }


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

    @Override
    public boolean isExit() {
        return false;
    }
}
