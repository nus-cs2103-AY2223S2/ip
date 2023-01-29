package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTaskTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ] bla bla bla", new ToDoTask("bla bla bla").toString());
    }
}
