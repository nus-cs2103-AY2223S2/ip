import org.junit.jupiter.api.Test;
import task.ToDos;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void todoTest(){
        ToDos todo = new ToDos("homework", 0);
        assertEquals("[T][0]homework", todo.toString());
    }
}