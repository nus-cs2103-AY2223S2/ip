package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void listTaskTest(){
        TaskList tl = new TaskList(new ArrayList<>());
        assertEquals(new ArrayList<Task>(), tl.getTaskList());
    }
}
