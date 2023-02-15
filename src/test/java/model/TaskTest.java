package model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import membot.model.Deadline;
import membot.model.Event;
import membot.model.Task;
import membot.model.ToDo;

public class TaskTest {
    @Test
    public void isIdValidTest() {
        assertFalse(Task.isIdValid(0));
        assertFalse(Task.isIdValid(1));

        new ToDo("todo 1");
        assertTrue(Task.isIdValid(1));
        assertFalse(Task.isIdValid(2));

        new ToDo("todo 2");
        assertTrue(Task.isIdValid(2));
    }

    @Test
    public void setStatusCompletedTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> Task.setStatusCompleted(0));
        assertThrows(IndexOutOfBoundsException.class, () -> Task.setStatusCompleted(1));

        Task t1 = new ToDo("todo 1");
        assertDoesNotThrow(() -> Task.setStatusCompleted(1));
        assertEquals("✔️", t1.printStatus());

        assertDoesNotThrow(() -> Task.setStatusCompleted(1));
        assertEquals("✔️", t1.printStatus());

        Task t2 = new Deadline("deadline 2", "today");
        assertDoesNotThrow(() -> Task.setStatusCompleted(2));
        assertEquals("✔️", t2.printStatus());

        Task t3 = new Event("event 3", "today 2pm", "today 4pm");
        assertDoesNotThrow(() -> Task.setStatusCompleted(3));
        assertEquals("✔️", t3.printStatus());
    }

    @Test
    public void setStatusNewTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> Task.setStatusNew(0));
        assertThrows(IndexOutOfBoundsException.class, () -> Task.setStatusNew(1));

        Task t1 = new ToDo("todo 1");
        assertDoesNotThrow(() -> Task.setStatusNew(1));
        assertEquals(" ", t1.printStatus());

        assertDoesNotThrow(() -> Task.setStatusNew(1));
        assertEquals(" ", t1.printStatus());

        Task t2 = new Deadline("deadline 2", "today");
        assertDoesNotThrow(() -> Task.setStatusNew(2));
        assertEquals(" ", t2.printStatus());

        Task t3 = new Event("event 3", "today 2pm", "today 4pm");
        assertDoesNotThrow(() -> Task.setStatusNew(3));
        assertEquals(" ", t3.printStatus());
    }

    @Test
    public void deleteTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> Task.delete(0));
        assertThrows(IndexOutOfBoundsException.class, () -> Task.delete(1));

        Task t1 = new ToDo("todo 1");
        assertDoesNotThrow(() -> assertEquals(Task.delete(1), t1));

        new ToDo("todo 2");
        Task t3 = new ToDo("todo 3");
        new ToDo("todo 4");

        assertDoesNotThrow(() -> assertEquals(Task.delete(2), t3));
    }

    @Test
    public void deleteLastTest() {
        assertThrows(NoSuchElementException.class, Task::deleteLast);
        assertThrows(NoSuchElementException.class, Task::deleteLast);

        Task t1 = new ToDo("todo 1");
        assertDoesNotThrow(() -> assertEquals(Task.deleteLast(), t1));

        new ToDo("todo 2");
        new ToDo("todo 3");
        Task t4 = new ToDo("todo 4");

        assertDoesNotThrow(() -> assertEquals(Task.deleteLast(), t4));
    }

    @Test
    public void listAllTest() {
        Task t1 = new ToDo("todo 1");
        Task t2 = new ToDo("todo 2");
        Task t3 = new ToDo("todo 3");
        Task t4 = new ToDo("todo 4");
        Task t5 = new ToDo("todo 5");

        assertArrayEquals(new String[]{t1.toString(), t2.toString(), t3.toString(), t4.toString(), t5.toString()},
                Task.listAll());
    }

    @Test
    public void listOneTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> Task.listOne(0));
        assertThrows(IndexOutOfBoundsException.class, () -> Task.listOne(1));

        Task t1 = new ToDo("todo 1");
        assertEquals(t1.toString(), Task.listOne(1));

        Task t2 = new ToDo("todo 2");
        Task t3 = new ToDo("todo 3");
        assertEquals(t2.toString(), Task.listOne(2));
        assertEquals(t3.toString(), Task.listOne(3));
        assertThrows(IndexOutOfBoundsException.class, () -> Task.listOne(4));
    }

    @Test
    public void printStatusTest() {
        Task t1 = new ToDo("todo 1");
        assertEquals(" ", t1.printStatus());

        Task.setStatusCompleted(1);
        assertEquals("✔️", t1.printStatus());
    }

    @AfterEach
    public void tearDown() {
        while (true) {
            try {
                Task.deleteLast();
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }
}
