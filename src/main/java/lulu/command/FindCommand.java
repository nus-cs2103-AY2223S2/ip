package lulu.command;

import lulu.Storage;
import lulu.TaskList;
import lulu.Ui;

import lulu.exception.InvalidCommandException;

/**
 * This command is used to find tasks from the user's TaskList.
 * When executed, a list of what the user is searching for in his/her TaskList is returned.
 * It has an additional description attribute for similar tasks to be found.
 */
public class FindCommand extends Command {
    private String description;

    public FindCommand(String rest) throws InvalidCommandException {
        if (rest.isEmpty()) {
            throw new InvalidCommandException();
        }
        this.description = rest;
    }

    /**
     * This method finds matching tasks from TaskList tasks upon execution.
     *
     * @param tasks   the TaskList to be searched
     * @param ui      the UI that displays messages
     * @param storage the Storage is not relevant in this command
     * @return a String that displays the matching tasks in a list format
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matched = tasks.find(this.description);
        return ui.showContainer(ui.listMatchText(), matched.printList());
    }
}
