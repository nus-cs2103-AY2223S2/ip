package Duke.Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void TodoTest() {
        assertEquals(String.format("[T][ ] %s\n", "Borrow book"), new Todo("Borrow book").toString());
    }
}
