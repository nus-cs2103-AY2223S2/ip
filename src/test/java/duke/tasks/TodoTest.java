import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import duke.tasks.Todo;


class MyFirstJUnitJupiterTests {

    @Test
    void myFirstTest() {
        Todo todo = new Todo(1, "read book");
        assertEquals("[T][ ] read book", todo.toString());
    }
}

