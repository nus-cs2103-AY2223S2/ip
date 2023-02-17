package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Task;

public class TaskTest {
    @Test
    public void getStatusIcon_normalTest_successful() {
        assertEquals(" ", new Task("Do homework").getStatusIcon());
    }

    @Test
    public void markAsDone_normalTest_successful() {
        Task task = new Task("Do assignment");
        task.markAsDone();
        assertEquals(true, task.isDone);
    }

    @Test
    public void markAsNotDone_normalTest_successful() {
        Task task = new Task("Do assignment");
        task.markAsNotDone();
        assertEquals(false, task.isDone);
    }
}
