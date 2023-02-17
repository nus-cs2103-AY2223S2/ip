package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private final String title;
    private final LocalDate by;

    public DeadlineCommand(String title, LocalDate by) {
        this.title = title;
        this.by = by;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newDeadline = tasks.addDeadline(this.title, this.by);
        return ui.printAddTask(newDeadline, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
