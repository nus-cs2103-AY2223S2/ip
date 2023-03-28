package Duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Commands.Deadline;
import Week2.src.main.TaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DeadlineTest {
    @Test
    public void testDaedline() throws IOException {
        TaskList taskList = new TaskList();
        Deadline.execute("test",  taskList);
        assertEquals("[D][ ] test", taskList.get(0).toString());
    }
}