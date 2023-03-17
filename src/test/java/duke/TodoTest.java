package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toStringTest(){
        Todos todos = new Todos("go home");
        assertEquals("[T][ ] go home", todos.toString());
        todos.setStatus(true);
        assertEquals("[T][X] go home", todos.toString());
    }

}
