package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toStringTest(){
        Todos todos = new Todos("go home");
        assertEquals(todos.toString(), "[T][ ] go home");
        todos.setStatus(true);
        assertEquals(todos.toString(), "[T][X] go home");
    }

}
