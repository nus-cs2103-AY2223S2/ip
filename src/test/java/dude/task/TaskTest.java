package dude.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void markStatus_success() {
        Todo todoTask = new Todo("Todo");
        Deadline deadlineTask = new Deadline("Deadline", "2023-01-19 1800");
        Task eventTask = new Event("Event", "2023-01-19 1800", "2023-01-21 2200");
        todoTask.mark();
        deadlineTask.mark();
        eventTask.mark();
        assertEquals("X", todoTask.getStatusIcon());
        assertEquals("X", deadlineTask.getStatusIcon());
        assertEquals("X", eventTask.getStatusIcon());
        todoTask.unmark();
        deadlineTask.unmark();
        eventTask.unmark();
        assertEquals(" ", todoTask.getStatusIcon());
        assertEquals(" ", deadlineTask.getStatusIcon());
        assertEquals(" ", eventTask.getStatusIcon());
    }
}

