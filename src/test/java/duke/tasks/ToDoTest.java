package duke.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    @Test
    void toDoTestUi(){
        ToDo todo = new ToDo("read book",false);
        assertEquals("\t[T][ ] read book", todo.toString());
    }
}