package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;

public class TaskListTest {
    @Test
    public void mark_validTaskNumber_successful() {
        Task t1 = new Deadline("submit iP", LocalDateTime.now());
        Task t2 = new Todo("submit Homework");
        TaskList tasks = new TaskList(new ArrayList<>());
        Storage storage = new Storage();
        tasks.addTask(t1, storage);
        tasks.addTask(t2, storage);
        tasks.mark(2, new Storage());
        assertEquals(true, tasks.get(1).isDone);
    }
}
