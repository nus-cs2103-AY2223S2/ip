package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import duke.util.TaskList;
import duke.util.service.ToDo;

public class TaskListTest {
    TaskList taskList = new TaskList();
    ToDo todoCheck = new ToDo("DRINK WATER");
    @Test
    void markDoneTest() {
        taskList = taskList.addTask(todoCheck);
        taskList.markDone(0);
        assertEquals("[T][X] DRINK WATER", taskList.getTaskAtIndex(0).toString());
    }

    @Test
    void unMarkTest() {
        taskList = taskList.addTask(todoCheck);
        taskList.markDone(0);
        taskList.unMark(0);
        assertEquals("[T][ ] DRINK WATER", taskList.getTaskAtIndex(0).toString());
    }

    @Test
    void addTaskTest() {
        taskList = taskList.addTask(todoCheck);
        assertEquals(1, taskList.getSize());
        assertEquals(todoCheck.toString(), taskList.getTaskAtIndex(0).toString());
    }

}
