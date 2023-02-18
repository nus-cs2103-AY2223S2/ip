package lulu.command;

import lulu.TaskList;
import lulu.Ui;
import lulu.Storage;

import lulu.exception.InvalidCommandException;

/**
 * This command is used to update tasks from the user's TaskList.
 * When executed, the specified task is updated from the user's TaskList.
 * It has an additional taskNumber attribute for the task to be updated.
 */
public class UpdateCommand extends Command {
    private int taskNumber;
    private String restOfCommand;

    public UpdateCommand(String rest) throws InvalidCommandException {
        if (rest.isEmpty()) {
            throw new InvalidCommandException();
        }
        int i = rest.indexOf(" ");
        String number = rest.substring(0, i);
        String restOfText = rest.substring(i + 1);
        try {
            this.taskNumber = Integer.valueOf(number);
            this.restOfCommand = restOfText;
            System.out.println(this.taskNumber);
            System.out.println(this.restOfCommand);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * This method updates the specified task upon execution.
     *
     * @param tasks   the TaskList with the task to be updated.
     * @param ui      the UI that displays messages
     * @param storage the storage is not relevant in this command
     * @return a String that displays the description of the task to be updated
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.update(taskNumber, restOfCommand);
            return ui.showContainer(tasks.getTaskDescription(taskNumber).toString());
        } catch (IndexOutOfBoundsException e) {
            return this.restOfCommand;
        }
    }
}