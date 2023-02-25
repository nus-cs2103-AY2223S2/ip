package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void addNewDeadlineTest() throws EmptyDescriptionException {
        Deadline deadline = new Deadline("test2", "Jan-01-2023 1200");
        assertEquals("[D][ ]test2 (by: Jan-01-2023 1200)", deadline.toString());
    }
}
