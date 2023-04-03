package duke.commands;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

import java.time.LocalDate;

/**
 * Represents an event command.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    protected String desc;
    protected LocalDate start;
    protected LocalDate end;

    /**
     * A constructor to initialize an event command.
     *
     * @param desc The description of the event object.
     * @param start The start time/date of the event object.
     * @param end The end time/date of the event object.
     */
    public EventCommand(String desc, LocalDate start, LocalDate end) {
        this.desc = desc;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, TextUi ui) {
        Task t = new Event(start, end, desc);
        taskList.addTask(t);
        storage.save(taskList);
        return ui.printTaskAdded(t, taskList);
    }
}
