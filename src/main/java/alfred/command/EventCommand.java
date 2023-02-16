package alfred.command;

import alfred.exceptions.AlfredException;
import alfred.exceptions.InvalidCommandException;
import alfred.storage.Storage;
import alfred.task.Event;
import alfred.task.Task;
import alfred.task.TaskList;
import alfred.ui.Ui;

/**
 * Represents an event command when a user wishes to add an event.
 */
public class EventCommand extends Command {

    private final String controlLine;

    /**
     * Constructs a Event command with the given command.
     * @param controlLine The full command to determine what is to be added.
     */
    public EventCommand(String controlLine) {
        this.controlLine = controlLine;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        String[] lineArr = controlLine.split("/from | /to ");
        if (lineArr.length < 2) { // not sure how to check if there's /from and /to
            throw new InvalidCommandException("event");
        }
        String[] descriptionArr = lineArr[0].split(" ", 2);
        if (descriptionArr.length == 1) {
            throw new InvalidCommandException("event");
        }
        Task task = new Event(lineArr[0], lineArr[1], lineArr[2]);
        tasks.addTask(task);

        String numTasks = tasks.getSize() == 1 ? "task" : "tasks";
        String output = String.format("Noted, task added: \n      %s\n"
                + "    Number of %s in the list: %d\n", task, numTasks, tasks.getSize());
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
