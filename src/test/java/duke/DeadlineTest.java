package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void TestAdditionOfDeadlineToTaskList() {
        TaskList tasks = new TaskList();
        tasks.addDeadline("test task /by 2023-01-02");
        assertEquals("1. [D][ ] test task (02/01/2023)\n", tasks.getTaskList());
    }
}
