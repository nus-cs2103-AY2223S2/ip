package duke.command;

import duke.storage.StorageList;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Event command with event and from and to timing.
 */
public class EventCommand extends Command {
    private String message;
    private String from;
    private String to;

    /**
     * Constructor for the event command.
     *
     * @param input
     */
    public EventCommand(String input) {
        String[] checkerSlash = input.split("/");
        String[] checkerEvent = checkerSlash[0].split("event ");
        String[] checkerFrom = checkerSlash[1].split("from ");
        String[] checkerTo = checkerSlash[2].split("to ");
        this.message = checkerEvent[1];
        this.from = checkerFrom[1];
        this.to = checkerTo[1];
    }

    /**
     * Method to execute the event task, which marks the from and to timings.
     *
     * @param tasks   - task list of the current tasks.
     * @param storage - database of the history of commands.
     * @return String Output when the event command is executed.
     */
    public String execute(TaskList tasks, StorageList storage) {
        Event t = new Event(message, from, to);
        tasks.addToList(t);
        return "Got it, I've added this task:" + t + tasks.statement();
    }

}
