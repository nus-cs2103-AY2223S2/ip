
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Event;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ]read book (from:2019-02-02 1800to:2019-02-02 1830)",
                new Event("read book","2019-02-02 1800/2019-02-02 1830").toString());
    }
}