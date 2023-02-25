package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void addNewToDoTest() throws EmptyDescriptionException {
        ToDo toDo = new ToDo("test1");
        assertEquals("[T][ ]test1", toDo.toString());
    }
}
