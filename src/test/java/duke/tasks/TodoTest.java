package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testToString() {
        assertEquals(new Todo("finish individual project", 0).toString(), "[T][ ] finish individual project");
    }
}
