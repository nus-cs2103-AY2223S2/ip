package alfred.command;

import alfred.exceptions.AlfredException;
import alfred.exceptions.InvalidCommandException;
import alfred.storage.Storage;
import alfred.task.Deadline;
import alfred.task.Task;
import alfred.task.TaskList;
import alfred.ui.Ui;

/**
 * Represents the Deadline command when a user wishes to add a deadline task.
 */
public class DeadlineCommand extends Command {


    private final String controlLine;

    /**
     * Constructs a Deadline command with the given command.
     * @param controlLine The full command to determine what is to be added.
     */
    public DeadlineCommand(String controlLine) {
        this.controlLine = controlLine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        String[] lineArr = controlLine.split("/by ");
        if (lineArr.length == 1) { // missing dates in the command
            throw new InvalidCommandException("deadline");
        }
        String[] descriptionArr = lineArr[0].split(" ", 2);
        if (descriptionArr.length == 1) { // no task name
            throw new InvalidCommandException("deadline");
        }
        Task task = new Deadline(lineArr[0], lineArr[1]);
        tasks.addTask(task);

        String numTasks = tasks.getSize() == 1 ? "task" : "tasks";
        String output = String.format("Noted, task added:\n" + ui.getIndent()
                + "%s\n" + "Number of %s in the list: %d\n",
                task, numTasks, tasks.getSize());
        return ui.getCommandMessage(output);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
