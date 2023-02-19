package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTodo_addTodo_successfullyAdd() {
        TaskList ls = new TaskList();
        try {
            ls.addToDo("todo buy milk");
            ArrayList<Task> list = ls.getList();
            Task task = list.get(0);
            assertEquals(task.toString(), "[T][ ] buy milk");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getSize_addFourTasks_returnsFour() {
        TaskList ls = new TaskList();
        try {
            ls.addToDo("todo buy milk");
            ls.addEvent("event party /from monday /to tuesday");
            ls.addToDo("todo buy cheese");
            ls.addDeadline("deadline return book /by 02/02/2020 1800");
            assertEquals(ls.getSize(), 4);
        } catch (DukeException e) {
            fail();
        }
    }
}
