package hachi.tasks;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void Test1() {
        Task test = new Event("task", "2023-01-28", "2023-01-29");
        assertEquals(test.toString(), "   [E][ ] task (from: 2023-01-28 (to: 2023-01-29)");
    }
}
