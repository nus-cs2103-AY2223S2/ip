package luffy.command;

import java.time.LocalDate;

import luffy.storage.TaskList;
import luffy.ui.Ui;

public class ViewCommand extends Command {
    private static final String FIND_COMMAND = "find";
    private LocalDate date;

    public ViewCommand(LocalDate date) {
        super(FIND_COMMAND);
        this.date = date;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.showSchedule(taskList, this.date);
    }
}
