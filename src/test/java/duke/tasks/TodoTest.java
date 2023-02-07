package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoToString(){
        ToDo t = new ToDo("run", true);
        assertEquals(t.toString(), "[T][X] run");
    }

    @Test
    public void todoSaveFormat(){
        ToDo t = new ToDo("run", true);
        assertEquals(t.saveFormat(), "T~1~run");
    }

}
