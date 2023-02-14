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
            String cmd = fullCommand[0];
            String description = fullCommand[1].trim();
            this.task = createTask(cmd, description);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyArgumentException("The description of " + fullCommand[0] + " command cannot be empty.");
        }
    }

    private Task createTask(String cmd, String description) throws DukeInvalidArgumentException,
            DukeEventOverlapException, DukeEmptyArgumentException {
        try {
            Task task = null;
            switch (cmd.toLowerCase()) {
            case "todo":
                task = new ToDos(description);
                break;
            case "deadline":
                String[] splitString = description.toLowerCase().split("/by", 2);
                task = new Deadlines(splitString[0].trim(), splitString[1].trim());
                System.out.println("a: "+splitString[0].trim());
                System.out.println("b: "+splitString[1].trim());
                break;
            case "event":
                String[] splitDescription = description.toLowerCase().split("/from", 2);
                String[] splitTime = splitDescription[1].split("/to", 2);
                task = new Events(splitDescription[0].trim(), splitTime[0].trim(), splitTime[1].trim());
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
