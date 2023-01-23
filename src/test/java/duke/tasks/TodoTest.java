package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTest() {
        Todo t = new Todo("return book");
        assertEquals("[T][ ] return book", t.toString());
    }
}
