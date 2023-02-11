package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

public class InvalidCommand implements Command {
    private String input;

    public InvalidCommand(String input) {
        this.input = input;
    }

    /**
     * Prints the exit message.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Invalid");
    }
}
