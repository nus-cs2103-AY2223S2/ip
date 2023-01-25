package chattime.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void getStatusIconTest() {
        Task testTask = new Task("Test");
        assertEquals(" ", testTask.getStatusIcon());
    }

    @Test
    public void markAsDone_getTaskStatusTest() {
        Task testTask = new Task("Test");
        testTask.markAsDone();
        assertTrue(testTask.getTaskStatus());
    }

    @Test
    public void toDataStringTest() {
        Task testTask = new Task("Test");
        assertEquals(" @ 0 @ Test", testTask.toDataString());
    }

    @Test
    public void onDateTest() {
        Task testTask = new Task("Test");
        assertFalse(testTask.onDate(LocalDate.of(2023, 1, 1)));
    }

    @Test
    public void toStringTest() {
        Task testTask = new Task("Test");
        assertEquals("[ ] Test", testTask.toString());
    }
}