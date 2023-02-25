package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Todo;
public class TodoTest {
    @Test
    public void testCreate() {
        assertEquals("[T][ ] sleep", new Todo("sleep").toString());
    }
}
