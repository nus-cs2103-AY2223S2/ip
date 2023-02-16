package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasktypes.ToDo;
public class ToDoTest {
    @Test
    public void testEmpty() throws DukeExceptions {
        String caughtIt = "";
        try {
            ToDo emptyOne = new ToDo("");
        } catch (DukeExceptions DE) {
            caughtIt = DE.toString();
        }
        assertEquals("Dumb orc, the description of a todo cannot be empty!", caughtIt);

    }

    @Test
    public void testUndoneValid() throws DukeExceptions {
        assertEquals("[T][ ]" + "test1", new ToDo("test1").toString());
    }

    @Test
    public void testDoneValid() throws DukeExceptions {
        ToDo testIfDone = new ToDo("test2");
        testIfDone.setDone();
        assertEquals("[T][X]" + "test2", testIfDone.toString());
    }
}
