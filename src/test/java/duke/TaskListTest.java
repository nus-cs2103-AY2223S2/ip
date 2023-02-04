package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void whenAddDeadline_thenReturnDeadlineAsTask() {
        TaskList tasks = new TaskList();
        tasks.addTask("test0 /by 2019-12-02", "deadline");
        assertEquals("1. [D][ ]test0  (by: 2 Dec 2019)", tasks.toString());
    }
}
