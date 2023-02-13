package duke.command;

import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeEventOverlapException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeIoException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

/**
 * Class for creating a add command, more specific for todo, deadline, and event command.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructor to create an add command.
     *
     * @param fullCommand User input command.
     * @throws DukeEmptyArgumentException indicate that a command has been passed an empty argument.
     * @throws DukeInvalidArgumentException indicate that a command has been passed an illegal argument.
     */
    public AddCommand(String[] fullCommand) throws DukeEmptyArgumentException, DukeInvalidArgumentException,
            DukeEventOverlapException {
        try {
            this.task = createTask(fullCommand[0], fullCommand[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("The description of " + fullCommand[0] + " command cannot be empty.");
        }
    }

    private Task createTask(String cmd, String description) throws DukeInvalidArgumentException,
            DukeEventOverlapException {
        try {
            Task task = null;
            switch (cmd) {
            case "todo":
                task = new ToDos(description);
                break;
            case "deadline":
                String[] s1 = description.split("/by ", 2);
                task = new Deadlines(s1[0], s1[1]);
                break;
            case "event":
                String[] s2 = description.split("/from ", 2);
                String[] s3 = s2[1].split(" /to ", 2);
                task = new Events(s2[0], s3[0], s3[1]);
            }
            assert task != null : "Empty task has been created";
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException("The description of " + cmd + " is invalid.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws DukeIoException {
        task.add(this.task);
        storage.updateData(this.task);
        return ui.responseToLAddTaskCommand(this.task, task);
    }
}
