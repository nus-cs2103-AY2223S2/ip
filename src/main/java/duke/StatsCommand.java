package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class StatsCommand extends Command {
    private LocalDateTime now;

    /**
     * Constructor for a Stats command.
     *
     * @param now Date the command was made.
     */
    public StatsCommand(LocalDateTime now) {
        this.now = now;
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) throws IOException {
        List<Task> taskList = tasks.getTasks();
        int noOfCompletedTasksPastWeek = 0;
        LocalDateTime weekAgo = now.minusWeeks(1);

        for (Task t : taskList) {
            if (t.isDone && isDateBetweenTwoDates(t.markDate, now, weekAgo)) {
                noOfCompletedTasksPastWeek++;
            }
        }

        return response.showStats(noOfCompletedTasksPastWeek);
    }

    private boolean isDateBetweenTwoDates(LocalDateTime date, LocalDateTime min, LocalDateTime max) {
        return date.isBefore(min) && date.isAfter(max);
    }
}
