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
     * @param tasks   - task list of the current tasks.
     * @param ui      - interface of the command.
     * @param storage - database of the history of commands.
     * @return
     */
    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        Event t = new Event(message, from, to);
        tasks.addToList(t);
        System.out.println("Got it, I've added this task:");
        System.out.println(t);
        tasks.statement();
        return true;
    }

}
