package duke.command;

import java.time.LocalDateTime;
import duke.task.TaskList;

public class SearchByDate extends Command {
    private LocalDateTime time;

    public SearchByDate(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.getTaskByTime(this.time).toString();
    }
}
