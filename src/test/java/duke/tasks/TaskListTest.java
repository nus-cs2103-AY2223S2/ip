package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void test2() {
        ArrayList<Task> arr = new ArrayList<>(10);
        TaskList t = new TaskList(arr);
        assertEquals(10, t.getSize());
    }
}
