package duke.task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {
    @Test
    public void parseTest(){
        Deadline deadline = new Deadline(
                "Return Book",
                "2023-12-12 18:00",
                false
        );
        assertEquals("D | false | Return Book | 2023-12-12 18:00",
                deadline.parse());
    }

}
