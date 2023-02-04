package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void isDoneTest() {
        Task t = new Task("do work", "T", "X");
        assertEquals(true, t.isTaskDone());
    }
}
