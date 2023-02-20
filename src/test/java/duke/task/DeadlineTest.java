package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        Task t = new Deadline("return book ", "2019-06-15");
        assertEquals("[D][ ] return book (by: Jun 15 2019)", t.toString());
    }

    @Test
    public void toStringTest2() {
        Task t = new Deadline("return book ", "2020-12-20");
        assertEquals("[D][ ] return book (by: Dec 20 2020)", t.toString());
    }


}
