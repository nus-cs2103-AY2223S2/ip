package duke.task;

import java.time.LocalDateTime;

public interface TaskCreator {
    public Task createTask(String desc, LocalDateTime start, LocalDateTime end);
}
