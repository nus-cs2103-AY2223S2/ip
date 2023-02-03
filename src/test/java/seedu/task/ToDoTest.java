package seedu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import task.ToDo;
public class ToDoTest {
    @Test
    public void testToDoDescription(){
        ToDo toDo = new ToDo("return book");
        assertEquals("[T][ ] return book", toDo.toString());
    }

    @Test
    public void testUnmark() {
        ToDo toDo = new ToDo("return book", true);
        toDo.unmark();
        assertEquals("[T][ ] return book", toDo.toString());
    }

    @Test
    public void testMark(){
        ToDo toDo = new ToDo("return book");
        toDo.mark();
        assertEquals("[T][X] return book", toDo.toString());
    }

}
