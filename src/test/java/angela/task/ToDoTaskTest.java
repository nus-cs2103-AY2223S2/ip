package angela.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTaskTest {
    @Test
    public void testToString() {
        assertEquals("[T][ ] bla bla bla", new ToDoTask("bla bla bla").toString());
    }

    @Test
    public void isUpcoming_daysZero() {
        assertEquals(false, new ToDoTask("bla bla bla").isUpcoming(0));
    }

    @Test
    public void isUpcoming_daysPositive() {
        assertEquals(false, new ToDoTask("bla bla bla").isUpcoming(1));
        assertEquals(false, new ToDoTask("bla bla bla").isUpcoming(2));
        assertEquals(false, new ToDoTask("bla bla bla").isUpcoming(3));
    }
}
