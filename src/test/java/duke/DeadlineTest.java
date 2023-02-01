package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void deadlineTest(){
        Deadline deadline = new Deadline("return book", "20/08/2023 12:00");
        assertEquals("return book (by: Aug 20 2023 12:00)", deadline.toString());
    }

}
