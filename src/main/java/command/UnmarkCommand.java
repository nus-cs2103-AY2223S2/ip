package command;

import task.TaskList;

import sys.Ui;
import sys.Storage;

import exception.DukeException;
import exception.InvalidCommandInputException;

public class UnmarkCommand extends Command {
    private String input;

    public UnmarkCommand(String input) {
        super("unmark \\d+");
        this.input = input;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Get index to unmark
        Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

        // Verify if task number is invalid:
        if (idx < 0 || idx >= tl.numberOfTasks()) {
            throw new InvalidCommandInputException("task.Task number is invalid!", "unmark");
        }

        // Unmark task and save changes
        tl.unmarkTask(idx);
        storage.save(tl);

        // Print statement
        System.out.println("OK, I've marked this task as not done yet:\n" + tl.getTask(idx));
    }
}
