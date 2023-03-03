package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToString() {
        try {
            Deadline deadline = new Deadline("Eat food", "08/02/2023 16:00");
            assertEquals("[D][ ][LOW] Eat food (by: 08 Feb 2023 4 PM)", deadline.toString());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void testGetBy() {
        try {
            Deadline deadline = new Deadline("Eat food", "08/02/2023 16:00");
            assertEquals("08/02/2023 16:00", deadline.getBy());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
