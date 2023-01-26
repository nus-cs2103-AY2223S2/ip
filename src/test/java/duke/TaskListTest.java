package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testMarkTask() {
        Todo todo = new Todo("read newspaper");
        todo.mark();
        assertEquals("[T][X] read newspaper", todo.toString());
    }

    @Test
    public void testDoesTaskExist() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertEquals(false, taskList.doesTaskExist(1));
    }

    @Test
    public void testDeleteTask() throws DukeException {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTodo("do homework");
        taskList.deleteTask(1);
        assertEquals("", taskList.toString());
    }

    @Test
    public void testUnmarkTask() {
        Todo todo = new Todo("read newspaper");
        todo.mark();
        todo.unMark();
        assertEquals("[T][ ] read newspaper", todo.toString());
    }
}
