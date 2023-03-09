package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class ToDoTest {

    @Test
    void toDoTestUi() {
        ToDo todo = new ToDo("read book", false);
        assertEquals("\t[T][ ] read book", todo.toString());
    }
}
