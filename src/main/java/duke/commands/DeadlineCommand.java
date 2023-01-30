package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

import java.time.LocalDate;

/**
 * Represents a deadline command.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    protected String desc;
    protected LocalDate by;

    /**
     * A constructor to initialize a deadline command.
     *
     * @param desc The description of the deadline object.
     * @param by The deadline of the deadline object.
     */
    public DeadlineCommand(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        System.out.println("Got it, I've added this task:");
        taskList.addDeadline(by, desc);
        storage.save(taskList);
    }

}
