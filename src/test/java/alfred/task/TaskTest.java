package alfred.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;


public class TaskTest {

    // should we use stubs?
    @Test
    public void testStringConversion_unMark() {
        String test = "[T][ ] This is a test task";
        assertEquals(test, new Task("This is a test task").toString());
    }

    @Test
    public void testMarkAsDone() {
        Task testTask = new Task("This is a test task");
        testTask.markAsDone();
        assertTrue(testTask.isDone);
    }

    @Test
    public void testUnmarkTask() {
        Task testTask = new Task("This is test task");
        testTask.markAsDone();
        testTask.unmarkTask();
        assertFalse(testTask.isDone);
    }

    @Test
    public void addToFile_unmark() {
        Task testTask = new Task("This is a test task");
        String test = " | 0 | This is a test task"
                + "\n";
        assertEquals(test, testTask.getFileFormat());
    }

    @Test
    public void testContainsDate() {
        LocalDate localDate = LocalDate.now();
        assertFalse(new Task("This is a test task").containsDate(localDate));
    }
}
