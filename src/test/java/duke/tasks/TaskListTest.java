package duke.tasks;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void test2() {
        ArrayList<Task> arr = new ArrayList<>(10);
        TaskList t = new TaskList(arr);
        assertEquals(10, t.getSize());
    }
}
