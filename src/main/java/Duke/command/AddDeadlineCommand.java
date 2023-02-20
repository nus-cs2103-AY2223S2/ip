package Duke.command;

import Duke.Exceptions.DukeMainExceptions;
import Duke.Exceptions.NoDeadlineException;
import Duke.Exceptions.NoDescriptionException;
import Duke.Storage.Storage;
import Duke.Tasks.Deadline;
import Duke.TaskList;
import Duke.Tasks.Task;
import Duke.Ui;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String dueDate;

    /**
     * Constructor that uses to create Deadline instance.
     * @param input The information about the task details which includes the
     *              description and due date.
     */
    public AddDeadlineCommand(String input) {
        String[] splitDesWithBy = input.split(" /by ", 2);
        this.description = splitDesWithBy[0].trim();

        if (description.equals("")) {
            throw new NoDescriptionException("The description of a deadline cannot be empty.");
        } else if (splitDesWithBy.length != 2) {
            throw new NoDeadlineException("The due date cannot be empty.");
        }

        this.dueDate = splitDesWithBy[1].trim();
    }
    public AddDeadlineCommand(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeMainExceptions {
        Deadline newDeadline = new Deadline(this.description, this.dueDate);
        assert this.description != null;
        tasks.addTask(newDeadline, storage);
        return ui.printAddedTask(newDeadline, tasks);
    }
}
