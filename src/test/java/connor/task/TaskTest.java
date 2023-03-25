package connor.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Testcases for the methods in Task.
 */
public class TaskTest {

    @Test
    public void getMarkTest() {
        Todo todo = new Todo("sleep");
        assertEquals(todo.getMark(), "[ ]");
        todo.mark();
        assertEquals(todo.getMark(), "[X]");
    }

    @Test
    public void dataFormatTest() {
        Todo todo = new Todo("sleep", true);
        Deadline deadline = new Deadline("sleep", LocalDateTime.parse("2020-05-05T20:00:00"));
        Event event = new Event("dinner", LocalDateTime.parse("2020-05-05T18:00:00"),
                LocalDateTime.parse("2020-05-05T20:00:00"));
        assertEquals(todo.dataFormat(), "T|true|sleep");
        assertEquals(deadline.dataFormat(), "D|false|sleep|2020-05-05T20:00");
        assertEquals(event.dataFormat(), "E|false|dinner|2020-05-05T18:00|2020-05-05T20:00");
    }

    @Test
    public void toStringTest() {
        Todo todo = new Todo("sleep", true);
        Deadline deadline = new Deadline("sleep", LocalDateTime.parse("2020-05-05T20:00:00"));
        Event event = new Event("dinner", LocalDateTime.parse("2020-05-05T18:00:00"),
                LocalDateTime.parse("2020-05-05T20:00:00"));
        assertEquals(todo.toString(), "[T][X] sleep");
        assertEquals(deadline.toString(), "[D][ ] sleep (by: MAY 5 2020 2000)");
        assertEquals(event.toString(), "[E][ ] dinner (from: MAY 5 2020 1800 to: MAY 5 2020 2000)");
    }

}
