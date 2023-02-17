package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

/**
 * DeadlineCommand class to represent command to create new Deadline task.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String desc;
    private LocalDateTime byWhen;

    /**
     * Constructor for DeadlineCommand
     * @param desc
     * @param byWhen
     */
    public DeadlineCommand(String desc, LocalDateTime byWhen) {
        this.desc = desc;
        this.byWhen = byWhen;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(desc, byWhen);
        tasks.addTask(newTask);
        String response = "OK! I've added:\n" + newTask
                + String.format("\nNow you have %d task(s) in the list.", tasks.getSize());
        Ui.showResponse(response);
        this.responseFromDukeAfterExecution = response;
    }
}
