package lulu.command;

import lulu.TaskList;
import lulu.Ui;
import lulu.Storage;

import lulu.exception.InvalidCommandException;

/**
 * This command is used to unmark tasks from the user's TaskList.
 * When executed, the specified task is unmarked from TaskList.
 * It has an additional taskNumber attribute for the task to be unmarked.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(String rest) throws InvalidCommandException {
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
     * This method unmarks the specified task upon execution.
     *
     * @param tasks   the TaskList with the task to be unmarked.
     * @param ui      the UI that displays messages
     * @param storage the storage is not relevant in this command
     * @return a String that displays the description of the task that is unmarked
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkTask(taskNumber);
        return ui.showContainer(ui.showUnmarkText(tasks.getTaskDescription(taskNumber).toString()));
    }
}
