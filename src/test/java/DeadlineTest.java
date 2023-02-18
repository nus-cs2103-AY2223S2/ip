import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {
    /** Test the creation of deadline task. */
    @Test
    public void addDeadlineTest() {
        Deadline deadline = new Deadline("Finish Single's Inferno s2 ", "2019-10-15");

        assertEquals("[D][ ] Finish Single's Inferno s2 (by: Oct 15 2019)", deadline.toString());
    }

    /** Test the marking of deadline task. */
    @Test
    public void markTest() {
        Deadline deadline = new Deadline("Finish Single's Inferno s2 ", "2019-10-15");
        deadline.mark();

        assertEquals("[D][X] Finish Single's Inferno s2 (by: Oct 15 2019)", deadline.toString());
    }

    /** Test the unmarking of deadline task. */
    @Test
    public void unmarkTest() {
        Deadline deadline = new Deadline("Finish Single's Inferno s2 ", "2019-10-15");
        deadline.mark();
        deadline.unmark();

        assertEquals("[D][ ] Finish Single's Inferno s2 (by: Oct 15 2019)", deadline.toString());
    }
}
