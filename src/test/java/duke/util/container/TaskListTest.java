package duke.util.container;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.model.task.Task;
import duke.model.task.TaskList;
import duke.model.task.TodoTask;

public class TaskListTest {

    private TaskList createTaskList(Task... tasks) {
        return new TaskList(Arrays.asList(tasks));
    }

    @Test
    public void shouldAddTaskCorrectly() {
        // Note that index starts from 1
        TaskList lst = createTaskList();
        Task task = new TodoTask("The Woman Who Sold the Moon");
        lst.add(task);
        assertEquals(1, lst.size());
        assertEquals(task, lst.get(1));
    }

    @Test
    public void shouldRemoveTaskCorrectly() {
        Task task = new TodoTask("Bad Apple");
        TaskList lst = createTaskList(task);
        Task taskRef = lst.remove(1);
        assertEquals(0, lst.size());
        assertTrue(task == taskRef);
    }

    @Test
    public void shouldListAllTasksWithCorrectIndicies() {
        // @formatter:off
        Task[] tasks = {
            new TodoTask("Bad Apple"),
            new TodoTask("The Woman Who Sold the Moon"),
            new TodoTask("Maiden's Capriccio")
        };
        // @formatter:on
        TaskList lst = createTaskList(tasks);
        String[] lines = lst.listAllTasks().split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            assertTrue(line.matches("\\d+\\..*"));
            int dotIndex = line.indexOf(".");
            assertEquals(i + 1, Integer.parseInt(line.substring(0, dotIndex)));
        }
    }

}
