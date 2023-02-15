package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.backend.TaskList;
import duke.exception.DukeException;
import duke.task.ToDo;



public class TaskListTest {
    @Test
    public void testTaskList() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getSize());
    }


    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("test"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("test"));
        try {
            taskList.deleteTask(0);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, taskList.getSize());
    }
}
