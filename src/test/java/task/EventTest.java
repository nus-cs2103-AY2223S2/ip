package task;

import org.junit.jupiter.api.Test;
import task.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toString_task_string() {
        assertEquals("[E][ ] christmas (from: DECEMBER 24, 2022 to: DECEMBER 25, 2022)",
                new Event("event christmas", "2022-12-24", "2022-12-25").toString());
    }
}
