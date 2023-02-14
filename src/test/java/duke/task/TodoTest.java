package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todoStringTest() {
        assertEquals(String.format("[T][ ] %s\n", "Borrow book"), new Todo("Borrow book").toString());
    }
}
