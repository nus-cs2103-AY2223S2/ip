package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void basicTest() {
        assertEquals(new ToDo("study for the midterms"),
                new String("[T][ ] study for the midterms"));
    }


    @Test
    public void markTest() {
        ToDo todo = new ToDo("study for the midterms");
        todo.mark();
        assertEquals(todo,
                new String("[T][X] study for the midterms"));
    }
}
