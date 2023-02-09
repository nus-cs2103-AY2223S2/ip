package task;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void isCorrectTaskTest() {
        Task task = new Task("eat");
        assertTrue(task.isCorrectTask("eat"));
    }
}
