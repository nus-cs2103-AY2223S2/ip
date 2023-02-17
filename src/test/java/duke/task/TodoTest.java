package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToString(){
        String title = "finish ip";
        Todo newTodo = new Todo(title);
        assertEquals(newTodo.toString(), "[T][ ] finish ip");
    }

    @Test
    public void testToSavedString(){
        String title = "finish ip";
        Todo newTodo = new Todo(title);
        assertEquals(newTodo.toSavedString(), "T|0|finish ip");
    }
}
