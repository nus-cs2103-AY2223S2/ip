package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void TestAdditionOfEventToTaskList() {
        TaskList tasks = new TaskList();
        tasks.addEvent("test task /from 2pm /to 6pm");
        assertEquals("1. [E][ ] test task (from: 2pm to: 6pm)\n", tasks.getTaskList());
    }

}
