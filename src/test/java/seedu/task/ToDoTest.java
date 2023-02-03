package seedu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import task.ToDo;
public class ToDoTest {

    public void testToDoDescription(){
        ToDo toDo = new ToDo("return book");
        assertEquals("[T][ ] return book", toDo.toString());
    }

    public void testUnmark() {
        ToDo toDo = new ToDo("return book", true);
        toDo.unmark();
        assertEquals("[T][ ] return book", toDo.toString());
    }

    public void testMark(){
        ToDo toDo = new ToDo("return book");
        toDo.mark();
        assertEquals("[T][X] return book", toDo.toString());
    }

}
