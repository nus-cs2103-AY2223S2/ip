package command;

import sys.Ui;
import sys.Storage;

import task.TaskList;

import exception.DukeException;
import exception.InvalidCommandInputException;

public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        super("mark \\d+");
        this.input = input;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Find index to mark
        Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

        // Verify if task number is invalid:
        if (idx < 0 || idx >= tl.numberOfTasks()) {
            throw new InvalidCommandInputException("task.Task number is invalid!", "mark");
        }

        // Mark task and save changes
        tl.markTask(idx);
        storage.save(tl);

        // Print statement
        System.out.println("Nice! I've marked this task as done:\n" + tl.getTask(idx));
    }
}
