package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class TaskListTest {
    @Test
    public void getters_validToDo_stringReturned() {
        ArrayList<Task> arr = new ArrayList<Task>();
        arr.add(new ToDo("Item"));
        arr.add(new Deadline("Essay", "2023-01-01 23:59"));
        arr.add(new Event("Activity",
                "2023-01-01 23:59",
                "2023-01-02 23:59"));
        TaskList list = new TaskList(arr);
        assertEquals(list.getSize(), 3);
    }
}
