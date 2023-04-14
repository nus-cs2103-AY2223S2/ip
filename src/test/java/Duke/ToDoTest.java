package Duke;

import Duke.Tasks.ToDo;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void changeFormatTest(){
        ToDo td = new ToDo("Homework");
        assertEquals(td.changeFormat(), "T / 0 / Homework");

    }
    @Test
    public void toStringTest(){
        ToDo td = new ToDo("Homework");
        assertEquals(td.toString(), "    [T][ ] Homework");

    }

}
