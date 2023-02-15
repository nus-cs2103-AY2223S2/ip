package spongebob.command;

import spongebob.exception.SpongebobEmptyArgumentException;
import spongebob.exception.SpongebobEventOverlapException;
import spongebob.exception.SpongebobInvalidArgumentException;
import spongebob.exception.SpongebobIoException;

import spongebob.storage.Storage;
import spongebob.task.Deadlines;
import spongebob.task.Events;
import spongebob.task.Task;
import spongebob.task.TaskList;
import spongebob.task.ToDos;
import spongebob.ui.Ui;

/**
 * Class for creating a add command, more specific for todo, deadline, and event command.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructor to create an add command.
     *
     * @param fullCommand User input command.
     * @throws SpongebobEmptyArgumentException indicate that a command has been passed an empty argument.
     * @throws SpongebobInvalidArgumentException indicate that a command has been passed an illegal argument.
     * @throws SpongebobEventOverlapException indicate there are overlapping tasks exist.
     */
    public AddCommand(String[] fullCommand) throws SpongebobEmptyArgumentException, SpongebobInvalidArgumentException,
            SpongebobEventOverlapException {
        try {
            String cmd = fullCommand[0];
            String description = fullCommand[1].trim();
            this.task = createTask(cmd, description);
        } catch (IndexOutOfBoundsException e) {
            throw new SpongebobEmptyArgumentException("The description of "
                    + fullCommand[0]
                    + " command cannot be empty.");
        }
    }

    private Task createTask(String cmd, String description) throws SpongebobInvalidArgumentException,
            SpongebobEventOverlapException {
        try {
            Task task = null;
            switch (cmd.toLowerCase()) {
            case "todo":
                task = new ToDos(description);
                break;
            case "deadline":
                String[] result = splitString("by", description);
                task = new Deadlines(result[0].trim(), result[1].trim());
                break;
            case "event":
                String[] tmp = splitString("from", description);
                String[] str = splitString("to", tmp[1]);
                task = new Events(tmp[0].trim(), str[0].trim(), str[1].trim());
            }
            assert task != null : "Empty task has been created";
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new SpongebobInvalidArgumentException("The description of " + cmd + " is invalid.");
        }
    }

    private String[] splitString(String str, String description) {
        String[] splitString = description.toLowerCase().split("/" + str, 2);
        return new String[] {description.substring(0, splitString[0].length()), splitString[1]};
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList task, Ui ui, Storage storage) throws SpongebobIoException {
        task.add(this.task);
        storage.updateData(this.task);
        return ui.responseToLAddTaskCommand(this.task, task);
    }
}
