package command;

import sys.Ui;
import sys.Storage;

import task.TaskList;
import task.Task;

import exception.DukeException;
import exception.InvalidCommandInputException;

public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        super("delete \\d+");
        this.input = input;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Find index of task to delete
        Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

        // Verify if task number is invalid:
        if (idx < 0 || idx >= tl.numberOfTasks()) {
            throw new InvalidCommandInputException("task.Task number is invalid!", "delete");
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
