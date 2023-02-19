package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void testGetDescription() {
        assertEquals("todo", new Command("todo").getDescription());
    }
    @Test
    public void testGetArguments() {
        String[] s = new String[] {"3pm", "tomorrow"};
        assertArrayEquals(s,
                new Command("event", "3pm", "tomorrow").getArguments());
    }
    
}
