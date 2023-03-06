package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void checkToDoString(){
        Task t = new Task("todo read book");
        assertEquals("[ ] todo read book", t.toString());
    }
}