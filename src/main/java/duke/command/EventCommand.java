package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;
import duke.task.Event;

import java.time.LocalDate;

/**
 * EventCommand that has description, start date and end date.
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";
    private String desc;
    private LocalDate from;
    private  LocalDate to;

    /**
     * Constructor for EventCommand.
     * @param desc
     * @param from
     * @param to
     */
    public EventCommand(String desc,LocalDate from,LocalDate to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes adding of event task to tasklist.
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newTask = new Event(desc,from,to);
        tasks.addTask(newTask);
    }
}
