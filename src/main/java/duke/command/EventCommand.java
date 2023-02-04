package duke.command;

import duke.storage.StorageList;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

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
        String[] eventCommand = input.split("/");
        String[] eventMessage = eventCommand[0].split("event ");
        String[] fromTiming = eventCommand[1].split("from ");
        String[] toTiming = eventCommand[2].split("to ");
        this.message = eventMessage[1];
        this.from = fromTiming[1];
        this.to = toTiming[1];
    }

    /**
     * Method to execute the event task, which marks the from and to timings.
     *
     * @param tasks   - task list of the current tasks.
     * @param ui      - interface of the command.
     * @param storage - database of the history of commands.
     * @return String Output when the event command is executed.
     */
    public String execute(TaskList tasks, Ui ui, StorageList storage) {
        Event t = new Event(message, from, to);
        tasks.addToList(t);
        return "Got it, I've added this task:" + t + tasks.getLengthMessage();
    }

}
