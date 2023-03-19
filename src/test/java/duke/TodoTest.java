package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void toStringTest() {
        Todos todos = new Todos("go home");
        assertEquals("[T][ ] go home", todos.toString());
        todos.setStatus(true);
        assertEquals("[T][X] go home", todos.toString());
    }

}
