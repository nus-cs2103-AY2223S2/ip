package eevee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskTest {
    @Test
    public void testMarkTask() {
        Task expectedTask = new Task("finish homework", "T", true);
        Task newTask = new Task("finish homework", "T");
        newTask.markDone();
        assertEquals(expectedTask.getDescription(),
                newTask.getDescription());
    }

    @Test
    public void testFormatDescription() {
        String expectedOutput = "T | 1 | eat lunch";
        Task task = new Task("eat lunch", "T", true);
        assertEquals(expectedOutput, task.formatDescription());
    }
}
