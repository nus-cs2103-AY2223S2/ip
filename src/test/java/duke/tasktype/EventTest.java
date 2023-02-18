package duke.tasktype;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventTest() {
        Event e = new Event("lash", "lapapa", "papala");
        assertEquals("[E][ ]lash (from: lapapa to: papala)", e.toString());
    }
}
