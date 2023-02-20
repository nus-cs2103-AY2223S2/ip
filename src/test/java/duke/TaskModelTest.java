package duke;
import duke.model.Task;
import duke.model.TaskModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class TaskModelTest {
    TaskModel model;
    @Test
    public void testAddTask() {
        model = new TaskModel();
        // delete any previously existing tasks
        List<Task> currentTaskList = model.getTasks();
        for (Task task: currentTaskList) {
            model.deleteTask(task);
        }
        model.createTask("task 1");
        model.createTask("task 2");
        model.createTask("task 3");
        for (Task task: model.getTasks()) {
            System.out.println(task);
        }
        assertEquals(3, model.getTasks().size());
        assertTrue(model.getTask(0).toString().contains("task 1"));
        assertTrue(model.getTask(1).toString().contains("task 2"));
        assertTrue(model.getTask(2).toString().contains("task 3"));
    }
}
