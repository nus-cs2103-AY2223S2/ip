package duke.tasktype;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todoTest() {
        Todo t = new Todo("lash");
        assertEquals("[T][ ]lash", t.toString());
    }
}
