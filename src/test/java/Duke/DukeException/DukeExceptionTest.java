package Duke.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeExceptionTest {
    @Test
    public void DukeExceptionTest() {
        String expected = "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________\n";
        assertEquals(expected, new DukeException("I'm sorry, but I don't know what that means :-(").toString());
    }
}
