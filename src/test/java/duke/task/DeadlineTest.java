package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void deadline_compareEquals() {
        Deadline deadline1 = new Deadline("Do project", "2000-01-04 2359");
        Deadline deadline2 = new Deadline("Do project", "2000-04-01 2359");
        Deadline deadline3 = new Deadline("Do assignment project", "2000-01-04 2359");
        Deadline deadline4 = new Deadline("Do project", "2000-01-04 2359");
        assertNotEquals(deadline1, deadline2);
        assertNotEquals(deadline1, deadline3);
        assertEquals(deadline1, deadline4);
        assertNotEquals(deadline2, deadline3);
        assertNotEquals(deadline3, deadline4);
    }

    @Test
    public void deadline_incorrectFormat_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, (() -> new Deadline(
                "Do thing", "2020-06-11")));
        assertThrows(IllegalArgumentException.class, (() -> new Deadline(
                "Do thing", "2359")));
        assertThrows(IllegalArgumentException.class, (() -> new Deadline(
                "Do thing", "2020-13-02 1500")));
    }
}
