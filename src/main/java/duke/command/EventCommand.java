package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;
import java.time.LocalDate;
public class EventCommand extends Command {
    private final String title;
    private final LocalDate from;
    private final LocalDate to;

    public EventCommand(String title, LocalDate from, LocalDate to) {
        this.title = title;
        this.from = from;
        this.to = to;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task newEvent = tasks.addEvent(this.title, this.from, this.to);
        return ui.printAddTask(newEvent, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

