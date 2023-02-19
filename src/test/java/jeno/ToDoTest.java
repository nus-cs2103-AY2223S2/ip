package jeno;

import jeno.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toLogTest() {
        ToDo todoTask = new ToDo("return book");
        String expectedLog = "T | 0 | return book\n";
        assertEquals(expectedLog, todoTask.toLog());
    }

    @Test
    public void markTest() {
        ToDo todoTask = new ToDo("return book");
        todoTask.markTask();
        String expectedLog = "T | 1 | return book\n";
        assertEquals(expectedLog, todoTask.toLog());
    }
}
