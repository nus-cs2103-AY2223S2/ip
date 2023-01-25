package command;

import sys.Ui;
import sys.Storage;

import task.TaskList;

import exception.DukeException;
import exception.InvalidDateFormatException;

public class OccursCommand extends Command {
    private String input;

    public OccursCommand(String input) {
        super("list");
        this.input = input;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        // Handle occurs
        String dateTime = this.input.substring(7);

        // Print tasks that contain deadline
        try {
            tl.printTasksOnDate(dateTime);
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
