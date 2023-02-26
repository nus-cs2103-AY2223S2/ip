package chattime.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

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
    public void isMatchDescriptionTest() {
        Task testTask = new Task("Test");
        assertTrue(testTask.isMatchDescription("Test"));
    }

    @Test
    public void taskWithCodeTest() {
        Task testTask = new Task("Test");
        assertEquals(testTask.toString(), testTask.taskWithCode());
    }

    @Test
    public void toDataStringTest() {
        Task testTask = new Task("Test");
        assertEquals(" @ 0 @ Test", testTask.toDataString());
    }

    @Test
    public void onDateTest() {
        Task testTask = new Task("Test");
        assertFalse(testTask.isOnDate(LocalDate.of(2023, 1, 1)));
    }

    @Test
    public void toStringTest() {
        Task testTask = new Task("Test");
        assertEquals("[ ] Test", testTask.toString());
    }
}
