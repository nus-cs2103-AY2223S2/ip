package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void testToString1() {
        Task todo = new ToDos("See the sunrise", false);
        String result = todo.toString();
        assertEquals("[T][ ] See the sunrise", result);
    }

    @Test
    public void testToString2() {
        Task todo = new Todo("See the sunrise", true);
        String result = todo.toString();
        assertEquals("[T][X] See the sunrise", result);
    }

}
