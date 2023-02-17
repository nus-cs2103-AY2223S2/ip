package seedu.duke;

import duke.tasks.Deadline;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testFetchingDescription() {
        Deadline deadline = new Deadline("return book", "2022-12-12 2330");
        assertEquals("[D][ ] return book (by: Dec 12 2022 2330)", deadline.toString());
    }

    @Test
    public void testMarking(){
        Deadline deadline = new Deadline("return book", "2022-12-12 2330");
        deadline.mark();
        assertEquals("[D][X] return book (by: Dec 12 2022 2330)", deadline.toString());
    }
}