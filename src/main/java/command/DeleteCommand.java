package command;

import sys.Ui;
import sys.Storage;

import task.TaskList;
import task.Task;

import exception.DukeException;
import exception.InvalidCommandInputException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * Constructor for DeleteCommand
     * @param input Input to parse.
     */
    public DeleteCommand(String input) {
        super("delete \\d+");
        this.input = input;
    }

    /**
     * Deletes a task from the task list and save the changes.
     *
     * @param tl the current list of tasks
     * @param ui the user interface running.
     * @param storage the storage location for the program.
     * @throws DukeException If an invalid input is given.
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Find index of task to delete
        Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

        // Verify if task number is invalid:
        if (idx < 0 || idx >= tl.numberOfTasks()) {
            throw new InvalidCommandInputException("Task number is invalid!", "delete");
        }

        // Delete task and save changes
        Task t = tl.deleteTask(idx);
        storage.save(tl);

        // Print statement
        System.out.println("Noted. I've removed this task:\n" + t
                + "\nNow you have " + tl.numberOfTasks()
                + (tl.numberOfTasks() > 1 ? " tasks" : " task")
                + " in the list.");
    }
}
