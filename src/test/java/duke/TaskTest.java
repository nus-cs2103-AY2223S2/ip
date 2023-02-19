package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private static final String TEST = "test";
    private static final Date DATE = new Date(LocalDate.now());

    @Test
    public void markAsDone_givenInstance_correctlySets() {
        Task todo = new Todo(TEST);
        Task deadline = new Deadline(TEST, DATE);
        Task event = new Event(TEST, DATE, DATE);

        todo.markAsDone();
        assertTrue(todo.isDone);
        deadline.markAsDone();
        assertTrue(deadline.isDone);
        event.markAsDone();
        assertTrue(event.isDone);
    }

    @Test
    public void markAsDone_givenDoneInstance_correctlySets() {
        Task todo = new Todo(TEST);
        Task deadline = new Deadline(TEST, DATE);
        Task event = new Event(TEST, DATE, DATE);

        todo.isDone = true;
        deadline.isDone = true;
        event.isDone = true;

        todo.markAsDone();
        assertTrue(todo.isDone);
        deadline.markAsDone();
        assertTrue(deadline.isDone);
        event.markAsDone();
        assertTrue(event.isDone);
    }

    @Test
    public void unmarkAsDone_givenInstance_correctlySets() {
        Task todo = new Todo(TEST);
        Task deadline = new Deadline(TEST, DATE);
        Task event = new Event(TEST, DATE, DATE);

        todo.isDone = true;
        deadline.isDone = true;
        event.isDone = true;

        todo.unmarkAsDone();
        assertFalse(todo.isDone);
        deadline.unmarkAsDone();
        assertFalse(deadline.isDone);
        event.unmarkAsDone();
        assertFalse(event.isDone);
    }

    @Test
    public void unmarkAsDone_givenUndoneInstance_correctlySets() {
        Task todo = new Todo(TEST);
        Task deadline = new Deadline(TEST, DATE);
        Task event = new Event(TEST, DATE, DATE);

        todo.unmarkAsDone();
        assertFalse(todo.isDone);
        deadline.unmarkAsDone();
        assertFalse(deadline.isDone);
        event.unmarkAsDone();
        assertFalse(event.isDone);
    }
}
