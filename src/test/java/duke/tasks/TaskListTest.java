package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void test2() {
        TaskList t = new TaskList();
        assertEquals(0, t.getSize());
    }
}
