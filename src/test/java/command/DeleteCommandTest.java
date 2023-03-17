package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dukeexeption.InvalidArgumentException;
import storage.TaskList;
import task.Task;
import task.Todo;

public class DeleteCommandTest {
    TaskList tasks;
    int initialCount;

    @BeforeEach
    public void init() {
        this.tasks = new TaskList(new ArrayList<>(List.of(new Task[] {
                new Todo("Demo")
        })));
        this.initialCount = this.tasks.countTask();
    }

    @Test
    public void shouldDeleteTask() {
        new DeleteCommand(0).run(this.tasks);
        assertEquals(1, this.initialCount - this.tasks.countTask());
    }

    @Test
    public void shouldNotDeleteOutOfIndex() {
        assertThrows(InvalidArgumentException.class, () -> new DeleteCommand(1).run(this.tasks));
    }
}
