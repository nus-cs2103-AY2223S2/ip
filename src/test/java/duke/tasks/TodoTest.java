package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todoTest() {
        Todo t = new Todo("return book");
        assertEquals("[T] [   ]  return book\n", t.toString());
    }
}
