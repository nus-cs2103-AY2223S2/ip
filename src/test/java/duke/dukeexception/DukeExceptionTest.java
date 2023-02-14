package duke.dukeexception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DukeExceptionTest {
    @Test
    public void dukeExceptionStringTest() {
        String expected = "I'm sorry, but I don't know what that means :-(\n";
        assertEquals(expected, new DukeException("I'm sorry, but I don't know what that means :-(").toString());
    }
}
