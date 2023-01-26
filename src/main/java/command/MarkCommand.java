package command;

import sys.Ui;
import sys.Storage;

import task.TaskList;

import exception.DukeException;
import exception.InvalidCommandInputException;

/**
 * Represents the command to mark a task as completed and save the changes.
 */
public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        super("mark \\d+");
        this.input = input;
    }

    /**
     * Mark a task in the task list and save the changes.
     *
     * @param tl the current list of tasks
     * @param ui the user interface running.
     * @param storage the storage location for the program.
     * @throws DukeException If an invalid input is given.
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Find index to mark
        Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

        // Verify if task number is invalid:
        if (idx < 0 || idx >= tl.numberOfTasks()) {
            throw new InvalidCommandInputException("Task number is invalid!", "mark");
        }

        // Mark task and save changes
        tl.markTask(idx);
        storage.save(tl);

        // Print statement
        System.out.println("Nice! I've marked this task as done:\n" + tl.getTask(idx));
    }
}
