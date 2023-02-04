package skittles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ToDoTest {

    @Test
    public void TodoStringTest(){
        assertEquals(String.format("[T][] %s\n", "Borrow book"), new ToDo("Borrow book").toString());
    }
}
