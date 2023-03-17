package command;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dukeexeption.InvalidArgumentException;
import storage.TaskList;
import task.Task;
import task.Todo;

public class UnmarkCommandTest {
    TaskList tasks;
    int initialCount;

    @BeforeEach
    public void init() {
        this.tasks = new TaskList(new ArrayList<>(List.of(new Task[] {
                new Todo("Demo", false),
                new Todo("Demo", true)
        })));
        this.initialCount = this.tasks.countTask();
    }

    @Test
    public void shouldUnmarkTask() {
        new UnmarkCommand(1).run(this.tasks);
        new UnmarkCommand(0).run(this.tasks);

        Task[] actual = this.tasks.indexTask().toArray(new Task[0]);

        Task[] expected = new Task[] {
                new Todo("Demo", false),
                new Todo("Demo", false)
        };

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotUnmarkOutOfIndex() {
        assertThrows(InvalidArgumentException.class, () -> new UnmarkCommand(2).run(this.tasks));
    }
}
