package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

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
    public void execute(TaskList taskList, Storage storage) {
        System.out.println("Got it, I've added this task:");
        taskList.addEvent(start, end, desc);
        storage.save(taskList);
    }
}
