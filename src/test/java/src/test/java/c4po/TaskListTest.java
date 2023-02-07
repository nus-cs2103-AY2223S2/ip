package src.test.java.c4po;

import org.junit.jupiter.api.Test;
import src.main.c4po.BotException;
import src.main.c4po.TaskList;
import src.main.c4po.ToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAddedTask() throws BotException {
        TaskList tasks = new TaskList();
        ToDo newTodo = new ToDo("testTodo", 10);
        tasks.addItem(newTodo);
        assertEquals(newTodo, tasks.getItem(1));
    }

}
