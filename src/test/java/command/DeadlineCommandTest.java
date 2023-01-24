package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.TaskList;
import task.Deadline;
import task.Task;

public class DeadlineCommandTest {
    TaskList tasks;

    @BeforeEach
    public void init() {
        this.tasks = new TaskList();
    }

    @Test
    public void shouldAddDeadline() {
        new DeadlineCommand("Demo", LocalDate.parse("2023-01-01")).run(this.tasks);
        Task actual = this.tasks.showTask(0);
        Deadline expected = new Deadline("Demo", LocalDate.parse("2023-01-01"));
        assertEquals(expected, actual);
    }
}
