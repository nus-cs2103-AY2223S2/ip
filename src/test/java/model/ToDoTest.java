package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import membot.model.Task;
import membot.model.TaskType;
import membot.model.ToDo;

public class ToDoTest {
    @Test
    public void getTaskTypeTest() {
        ToDo t1 = new ToDo("test todo 1");
        assertEquals(TaskType.TODO, t1.getTaskType());
    }

    @Test
    public void getDeadlineTest() {
        ToDo t1 = new ToDo("test todo 1");
        assertEquals("~", t1.getDeadline());
    }

    @Test
    public void getStartDateTimeTest() {
        ToDo t1 = new ToDo("test todo 1");
        assertEquals("~", t1.getStartDateTime());
    }

    @Test
    public void getEndDateTimeTest() {
        ToDo t1 = new ToDo("test todo 1");
        assertEquals("~", t1.getEndDateTime());
    }

    @Test
    public void toStringTest() {
        ToDo t1 = new ToDo("test todo 1");
        assertEquals(String.format("%s[%s] %s", ToDo.TAG, t1.printStatus(), t1.getTitle()), t1.toString());
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
