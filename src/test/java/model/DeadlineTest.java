package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import membot.model.Deadline;
import membot.model.Task;
import membot.model.TaskType;

public class DeadlineTest {
    @Test
    public void getTaskTypeTest() {
        Deadline d1 = new Deadline("test deadline 1", "today");
        assertEquals(TaskType.DEADLINE, d1.getTaskType());
    }

    @Test
    public void getDeadlineTest() {
        Deadline d1 = new Deadline("test deadline 1", "today");
        assertEquals("today", d1.getDeadline());

        Deadline d2 = new Deadline("test deadline 2", "12/02/2022 13:01");
        assertEquals("12/02/2022 13:01", d2.getDeadline());

        Deadline d3 = new Deadline("test deadline 2", "");
        assertEquals("", d3.getDeadline());
    }

    @Test
    public void getStartDateTimeTest() {
        Deadline d1 = new Deadline("test deadline 1", "today");
        assertEquals("~", d1.getStartDateTime());
    }

    @Test
    public void getEndDateTimeTest() {
        Deadline d1 = new Deadline("test deadline 1", "today");
        assertEquals("~", d1.getEndDateTime());
    }

    @Test
    public void toStringTest() {
        Deadline d1 = new Deadline("test deadline 1", "today");
        assertEquals(String.format("%s[%s] %s (by: %s)",
                Deadline.TAG,
                d1.printStatus(),
                d1.getTitle(),
                d1.getDeadline()),
                d1.toString()
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
