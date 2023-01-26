package connor.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskTest {

    @Test
    public void getMarkTest() {
        Todo todo = new Todo("sleep");
        assertEquals(todo.getMark(), "[ ]");
        todo.mark();
        assertEquals(todo.getMark(), "[X]");
    }

    @Test
    public void formatDateTimeTest() {
        Deadline deadline = new Deadline("sleep", "2020-05-05 2000");
        String output = deadline.formatDateTime(LocalDateTime.parse("2020-05-05T18:00:00"));
        assertEquals(output, "MAY 5 2020 1800");
    }

    @Test
    public void dateTimeFormatTest() {
        Todo todo = new Todo("sleep", true);
        String str = todo.dateTimeFormat("2020-05-05 1800");
        assertEquals(str, "2020-05-05T18:00:00");
    }

    @Test
    public void dataFormatTest() {
        Todo todo = new Todo("sleep", true);
        Deadline deadline = new Deadline("eat", "2020-05-05 2000");
        Event event = new Event("dinner", "2020-05-05 1800", "2020-05-05 2200");
        assertEquals(todo.dataFormat(), "T|true|sleep");
        assertEquals(deadline.dataFormat(), "D|false|eat|2020-05-05T20:00:00");
        assertEquals(event.dataFormat(), "E|false|dinner|2020-05-05T18:00:00|2020-05-05T22:00:00");
    }

    @Test
    public void toStringTest() {
        Todo todo = new Todo("sleep", true);
        Deadline deadline = new Deadline("eat", "2020-05-05 2000");
        Event event = new Event("dinner", "2020-05-05 1800", "2020-05-05 2200");
        assertEquals(todo.toString(), "[T][X] sleep");
        assertEquals(deadline.toString(), "[D][ ] eat (by: MAY 5 2020 2000)");
        assertEquals(event.toString(), "[E][ ] dinner (from: MAY 5 2020 1800 to: MAY 5 2020 2200)");
    }

}
