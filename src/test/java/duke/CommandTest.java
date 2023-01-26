package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void testGetDescription() {
        assertEquals("todo", new Command("todo", new ArrayList<String>()).getDescription());
    }
    @Test
    public void testGetArguments() {
        List<String> lst = new ArrayList<>();
        lst.add("3pm");
        lst.add("tomorrow");
        assertEquals(lst, new Command("event", lst).getArguments());
    }
    
}
