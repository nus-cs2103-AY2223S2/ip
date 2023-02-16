package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasktypes.Deadlines;

public class DeadlinesTest {
    @Test
    public void testEmpty() throws DukeExceptions {
        String caughtIt = "";
        try {
            Deadlines emptyOne = new Deadlines("");
        } catch (DukeExceptions DE) {
            caughtIt = DE.toString();
        }
        assertEquals("Dumb orc, the description of a deadline cannot be empty!", caughtIt);
    }

    @Test
    public void testInvalidDate() throws DukeExceptions {
        Deadlines testIt = new Deadlines("test0 /by 12/12/2023");
        assertEquals("This task does not have a valid due date! Please input the date in this format: YYYY-MM-DD",
                testIt.taskDate());
    }
    @Test
    public void testUndoneWithDate() throws DukeExceptions {
        Deadlines testIt = new Deadlines("test1 /by 2023/12/12 1800");
        assertEquals("[D][ ]" + "test1" + " (by: Dec 12 2023 18:00)", testIt.toString());
    }

    @Test
    public void testUndoneWithoutDate() throws DukeExceptions {
        Deadlines testIt = new Deadlines("test2 /by Monday");
        assertEquals("[D][ ]" + "test2" + " (by: Monday)", testIt.toString());
    }

    @Test
    public void testDoneWithDate() throws DukeExceptions {
        Deadlines testIt = new Deadlines("test3 /by 2023/10/03 1800");
        testIt.setDone();
        assertEquals("[D][X]" + "test3" + " (by: Oct 3 2023 18:00)", testIt.toString());
    }

    @Test
    public void testDoneWithoutDate() throws DukeExceptions {
        Deadlines testIt = new Deadlines("test4 /by Tuesday");
        testIt.setDone();
        assertEquals("[D][X]" + "test4" + " (by: Tuesday)", testIt.toString());
    }


}
