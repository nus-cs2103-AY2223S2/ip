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

    public void execute(TaskList tasks, UI ui, Storage storage) {
        TaskList matched = tasks.find(this.description);
        ui.showLine();
        ui.listMatchText();
        int length = matched.getSize();
        for (int i = 0; i < length; i++) {
            System.out.print(i + 1);
            System.out.println(". " + matched.get(i));
        }
        ui.showLine();
    }
}
