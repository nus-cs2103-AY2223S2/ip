package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventTest() {
        assertEquals("[E][] bring books(from: 9 AUGUST 2022, 8pm to: 9 AUGUST 2023, 8pm )",
                new Event("bring books", "2022-08-09 8pm", "2023-08-09 8pm").toString());
    }
}
