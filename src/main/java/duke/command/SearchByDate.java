package duke.command;

import java.time.LocalDateTime;
import duke.task.TaskList;

public class SearchByDate extends Commands {
    private LocalDateTime time;

    public SearchByDate(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks) {
        System.out.println(tasks.getTaskByTime(this.time).toString());
    }
}
