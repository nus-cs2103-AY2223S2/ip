package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.util.service.ToDo;

public class TaskListTest {
    private TaskList taskList = new TaskList();
    private ToDo todoCheck = new ToDo("DRINK WATER");
    @Test
    void markDoneTest() {
        taskList = taskList.addTask(todoCheck);
        taskList.markDone(0);
        assertEquals("[T][X] DRINK WATER", taskList.getTask(0).toString());
    }

    @Test
    void unMarkTest() {
        taskList = taskList.addTask(todoCheck);
        taskList.markDone(0);
        taskList.unMark(0);
        assertEquals("[T][ ] DRINK WATER", taskList.getTask(0).toString());
    }

    @Test
    void addTaskTest() {
        taskList = taskList.addTask(todoCheck);
        assertEquals(1, taskList.getSize());
        assertEquals(todoCheck.toString(), taskList.getTask(0).toString());
    }

}
