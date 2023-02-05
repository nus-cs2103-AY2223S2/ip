package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void task_containsKeyword() {
        Task task = new Task("Task with certain keyword") {
            @Override
            public String getRecreateCommand(int id) {
                return "";
            }
        };
        assertEquals(true, task.contains("keyword"));
        assertEquals(false, task.contains("notKeyword"));
    }
}
