package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.TaskList;
import task.Task;
import task.Todo;

public class TodoCommandTest {
    TaskList tasks;

    @BeforeEach
    public void init() {
        this.tasks = new TaskList();
    }

    @Test
    public void shouldAddTodo() {
        new TodoCommand("Demo").run(this.tasks);
        Task actual = this.tasks.showTask(0);
        Todo expected = new Todo("Demo");
        assertEquals(expected, actual);
    }
}
