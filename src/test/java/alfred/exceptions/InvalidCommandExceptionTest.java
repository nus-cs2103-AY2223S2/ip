package alfred.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InvalidCommandExceptionTest {

    @Test
    public void testStringConversion_bye() {
        String test = "ERROR! To exit the program type: bye\n";
        assertEquals(test, new InvalidCommandException("bye").toString());
    }

    @Test
    public void testStringConversion_mark() {
        String test = "ERROR! To mark a task: mark <task-index>\n";
        assertEquals(test, new InvalidCommandException("mark").toString());
    }
}
