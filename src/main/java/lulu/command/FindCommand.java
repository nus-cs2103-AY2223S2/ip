package lulu.command;

import lulu.Storage;
import lulu.TaskList;
import lulu.UI;

import lulu.exception.InvalidCommandException;

public class FindCommand extends Command {
    private String description;

    public FindCommand(String rest) throws InvalidCommandException {
        if (rest.isEmpty()) {
            throw new InvalidCommandException();
        }
        this.description = rest;
    }

    public String execute(TaskList tasks, UI ui, Storage storage) {
        TaskList matched = tasks.find(this.description);
        return ui.showContainer(ui.listMatchText(), matched.printList());
    }
}
