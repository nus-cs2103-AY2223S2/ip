package Duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Commands.Todo;
import Week2.src.main.TaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TodoTest {
    @Test
    public void tesTodo() throws IOException {
        TaskList taskList = new TaskList();
        Todo.execute("test",  taskList);
        assertEquals("[T][ ] test", taskList.get(0).toString());
    }
}