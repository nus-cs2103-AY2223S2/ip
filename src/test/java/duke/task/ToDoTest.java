package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void addToDo() {
        ToDo todo = new ToDo("CS3230 HW1");
        assertEquals("[T][] CS3230 HW1", todo.getStatusIcon());
    }

    @Test
    public void mark() {
        ToDo todo = new ToDo("CS3230 HW1");
        todo.markTask();
        assertEquals("[T][X] CS3230 HW1", todo.getStatusIcon());
    }

    @Test
    public void unmark() {
        ToDo todo = new ToDo("CS3230 HW1");
        todo.markTask();
        todo.unmarkTask();
        assertEquals("[T][] CS3230 HW1", todo.getStatusIcon());
    }

    @Test
    public void encode() {
        ToDo todo = new ToDo("CS3230 HW1");
        todo.markTask();
        assertEquals("todo ### true ### CS3230 HW1", todo.encode());
    }
}
