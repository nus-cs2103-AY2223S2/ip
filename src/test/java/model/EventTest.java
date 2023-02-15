package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import membot.model.Event;
import membot.model.Task;
import membot.model.TaskType;

public class EventTest {
    @Test
    public void getTaskTypeTest() {
        Event e1 = new Event("test event 1", "today 2pm", "today 4pm");
        assertEquals(TaskType.EVENT, e1.getTaskType());
    }

    @Test
    public void getDeadlineTest() {
        Event e1 = new Event("test event 1", "today 2pm", "today 4pm");
        assertEquals("~", e1.getDeadline());
    }

    @Test
    public void getStartDateTimeTest() {
        Event e1 = new Event("test event 1", "today 2pm", "today 4pm");
        assertEquals("today 2pm", e1.getStartDateTime());

        Event e2 = new Event("test event 1", "12/02/2022 12:09", "today 4pm");
        assertEquals("12/02/2022 12:09", e2.getStartDateTime());

        Event e3 = new Event("test event 1", "", "today 4pm");
        assertEquals("", e3.getStartDateTime());
    }

    @Test
    public void getEndDateTimeTest() {
        Event e1 = new Event("test event 1", "today 2pm", "today 4pm");
        assertEquals("today 4pm", e1.getEndDateTime());

        Event e2 = new Event("test event 1", "12/02/2022 12:09", "12/02/2022 14:09");
        assertEquals("12/02/2022 14:09", e2.getEndDateTime());

        Event e3 = new Event("test event 1", "", "");
        assertEquals("", e3.getEndDateTime());
    }

    @Test
    public void toStringTest() {
        Event e1 = new Event("test event 1", "12/02/2022 12:09", "12/02/2022 14:09");
        assertEquals(String.format("%s[%s] %s (from: %s to: %s)",
                Event.TAG,
                e1.printStatus(),
                e1.getTitle(),
                e1.getStartDateTime(),
                e1.getEndDateTime()),
                e1.toString()
        );
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
