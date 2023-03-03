package task;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TasklistTest {
    @Test
    public void testAddTodoTask() {
        Todo todo = new Todo("Add this Task");
        Tasklist testList = new Tasklist();
        testList.add(todo);
        assertEquals(1, testList.size());
    }
    @Test
    public void testAddDeadlineTask() {
        Deadline deadline = new Deadline("Add this Task", LocalDate.of(2022,12,22));
        Tasklist testList = new Tasklist();
        testList.add(deadline);
        assertEquals(1, testList.size());
    }

    @Test
    public void testMarkAsDone() {
        Todo todo = new Todo("Add this Task");
        Tasklist testList = new Tasklist();
        testList.add(todo);
        testList.markTaskAsDone(0);
        assertEquals("X", testList.getTask(1).getStatusICon());
    }

    @Test
    public void testUnMark() {
        Todo todo = new Todo("Add this Task");
        Tasklist testList = new Tasklist();
        testList.add(todo);
        testList.markTaskAsDone(0);
        testList.unmarkTask(0);
        assertEquals(" ", testList.getTask(1).getStatusICon());
    }
}
