package lulu.command;

import lulu.TaskList;
import lulu.Ui;
import lulu.Storage;

import lulu.exception.InvalidCommandException;

/**
 * This command is used to delete tasks from the user's TaskList.
 * When executed, the specified task is removed from the TaskList.
 * It has an additional taskNumber attribute for the task to be deleted.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(String rest) throws InvalidCommandException {
        if (rest.isEmpty()) {
            throw new InvalidCommandException();
        }
        String[] restCommand = rest.split(" ");
        if (restCommand.length > 1) {
            throw new InvalidCommandException();
        }
        try {
            this.taskNumber = Integer.valueOf(restCommand[0]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * This method deletes a task from tasks upon execution.
     *
     * @param tasks   the TaskList to be deleted with a task
     * @param ui      the UI that displays messages
     * @param storage the Storage is not relevant in this command
     * @return a String that displays the task deleted
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String text = ui.showContainer(ui.showDeleteText(tasks.getTaskDescription(taskNumber), tasks.getSize() - 1));
        tasks.remove(taskNumber);
        return text;
    }
}
