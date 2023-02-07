package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;
import duke.task.Deadline;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String desc;
    private LocalDateTime byWhen;

    public DeadlineCommand(String desc,LocalDateTime byWhen) {
        this.desc = desc;
        this.byWhen = byWhen;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(desc,byWhen);
        tasks.addTask(newTask);
        String response = "OK! I've added:\n" + newTask.toString() +
                String.format("\nNow you have %d task(s) in the list.", tasks.getSize());
        Ui.showResponse(response);
        this.responseFromDukeAfterExecution = response;
    }
}
