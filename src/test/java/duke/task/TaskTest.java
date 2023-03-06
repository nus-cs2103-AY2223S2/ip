package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getTask_alphabetsNumbers_success() {
        String input = "abcdefADCDEF123456";
        Task task = new Task(input);
        assertEquals(input, task.getTask());
    }

    @Test
    public void saveString_markAsDone_success() {
        Task task = new Task("the task");
        task.setDone();
        assertEquals("Task | 1 | the task", task.saveString());
    }
}
