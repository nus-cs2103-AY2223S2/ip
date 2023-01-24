package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ] bla bla bla", new ToDoTask("bla bla bla").toString());
    }
}
