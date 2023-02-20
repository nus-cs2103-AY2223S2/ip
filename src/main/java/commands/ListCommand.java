package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

public class ListCommand implements Command {
    private String input;

    public ListCommand(String input) {
        this.input = input;
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IllegalArgumentException{
        if (input.trim().equals("list")) {
            return ui.printList(taskList);
        }
        throw new IllegalArgumentException();
    }
}
