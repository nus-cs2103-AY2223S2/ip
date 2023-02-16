package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasktypes.Events;
public class EventsTest {

    @Test
    public void testEmpty() {
        String caughtIt = "";
        try {
            Events emptyOne = new Events("");
        } catch (DukeExceptions DE) {
            caughtIt = DE.toString();
        }
        assertEquals("Dumb orc, the description of an event cannot be empty!", caughtIt);
    }

    @Test
    public void testUndoneValid() throws DukeExceptions {
        assertEquals("[E][ ]test1 (from: Monday to: Thursday)",
                new Events("test1 /from Monday /to Thursday").toString());
    }

    @Test
    public void testDoneValid() throws DukeExceptions {
        Events toCheck = new Events("test2 /from now /to next month");
        toCheck.setDone();
        assertEquals("[E][X]test2 (from: now to: next month)", toCheck.toString());
    }
}
