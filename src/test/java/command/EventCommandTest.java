package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dukeexeption.InvalidArgumentException;
import storage.TaskList;
import task.Event;
import task.Task;

public class EventCommandTest {
    TaskList tasks;

    @BeforeEach
    public void init() {
        this.tasks = new TaskList();
    }

    @Test
    public void shouldAddEvent() {
        new EventCommand("Demo", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-03")).run(this.tasks);
        Task actual = this.tasks.showTask(0);
        Event expected = new Event("Demo", LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-03"));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotAllowEndDateBeforeStartDate() {
        assertThrows(InvalidArgumentException.class, () ->
                new EventCommand("Demo", LocalDate.parse("2023-01-02"), LocalDate.parse("2023-01-01"))
                        .run(this.tasks)
        );
    }
}
