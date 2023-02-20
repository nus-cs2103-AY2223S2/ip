package duke.main;

import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {

    Todo todoOne, todoTwo, todoThree, todoFour;
    TaskList taskList;
    List<Task> allTask = new ArrayList<>();

    @BeforeEach
    void setUp() {
        todoOne = new Todo(1, true, "eat food", 1);
        todoTwo = new Todo(2, false, "buy food", 2);
        todoThree = new Todo(2, true, "buy food", 2);
        todoFour = new Todo(1, false, "eat food", 2);
        allTask.add(todoOne);
        allTask.add(todoTwo);
        taskList = new TaskList(allTask);
    }
    @Test
    public void markAsDoneTest() {
        assertEquals(todoThree, taskList.markTaskAsDone(todoTwo, 0));
    }

    @Test
    public void unmarkAsUndoneTest() {
        assertEquals(todoFour, taskList.unmarkTaskAsUndone(todoOne, 0));
    }
}
