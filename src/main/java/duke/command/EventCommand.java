package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * EventCommand that represents command to add an Event task to task list.
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";
    private String desc;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for EventCommand.
     * @param desc description of event
     * @param from start datetime
     * @param to end datetime
     */
    public EventCommand(String desc, LocalDateTime from, LocalDateTime to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newTask = new Event(desc, from, to);
        tasks.addTask(newTask);
        String response = "OK! I've added:\n" + newTask
                + String.format("\nNow you have %d task(s) in the list.", tasks.getSize());
        Ui.showResponse(response);
        this.responseFromDukeAfterExecution = response;
    }
}
