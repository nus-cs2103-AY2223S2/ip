package command;

import sys.Ui;
import sys.Storage;

import task.TaskList;
import task.Task;

import exception.DukeException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private String input;

    public AddCommand(String input) {
        super("");
        this.input = input;
    }

    /**
     * Adds a task to the task list and save it.
     *
     * @param tl the current list of tasks
     * @param ui the user interface running.
     * @param storage the storage location for the program.
     * @throws DukeException If an invalid input is given.
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Add task
        Task t = tl.addTask(input);
        storage.save(tl);
        System.out.println("Got it. I've added this task:\n" + t +
                "\nNow you have " + tl.numberOfTasks() + (tl.numberOfTasks() > 1 ? " tasks" : " task") + " in the list.");
    }
}
