package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;
import duke.task.Event;

import java.time.LocalDateTime;

/**
 * EventCommand that has description, start date and end date.
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";
    private String desc;
    private LocalDateTime from;
    private  LocalDateTime to;

    /**
     * Constructor for EventCommand.
     * @param desc
     * @param from
     * @param to
     */
    public EventCommand(String desc,LocalDateTime from,LocalDateTime to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newTask = new Event(desc,from,to);
        tasks.addTask(newTask);
        String response = "OK! I've added:\n" + newTask.toString() +
                String.format("\nNow you have %d task(s) in the list.", tasks.getSize());
        Ui.showResponse(response);
        this.responseFromDukeAfterExecution = response;
    }

}
