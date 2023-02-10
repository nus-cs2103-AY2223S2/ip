package lulu.command;

import lulu.TaskList;
import lulu.UI;
import lulu.Storage;

import lulu.exception.InvalidCommandException;

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
        //String[] restCommand = rest.split(" ");
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
     * This method unmarks the specified task upon execution.
     *
     * @param tasks   the TaskList with the task to be unmarked.
     * @param ui      the UI that displays messages
     * @param storage the storage is not relevant in this command
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        try {
            tasks.update(taskNumber, restOfCommand);
            return ui.showContainer(tasks.getTaskDescription(taskNumber).toString());
        } catch (IndexOutOfBoundsException e) {
            return this.restOfCommand;
        }
    }
}