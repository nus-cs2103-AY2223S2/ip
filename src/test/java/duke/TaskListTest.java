package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addDeadlineTest_whenAddDeadline_thenReturnDeadlineAsTask() {
        TaskList tasks = new TaskList();
        tasks.addDeadline("deadline test0 /by 2019-12-02 /note on canvas");
        assertEquals("1. [D][ ] test0 (by: 2 Dec 2019)\nNote: on canvas", tasks.toString());
    }
}
