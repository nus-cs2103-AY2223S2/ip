package lulu.command;

import lulu.TaskList;
import lulu.UI;
import lulu.Storage;

import lulu.exception.InvalidCommandException;

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
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        tasks.unmarkTask(taskNumber);
        return ui.showContainer(ui.showUnmarkText(tasks.getTaskDescription(taskNumber).toString()));
    }
}
