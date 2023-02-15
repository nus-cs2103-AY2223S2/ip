package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void testDeadlineToString() {
        assertEquals("[D][X] do homework (by: Jan-12-2023 15:00)",
                new Deadline("do homework", "Jan-12-2023 15:00", true).toString());
    }

    @Test
    public void testMarkDeadline() {
        Deadline mockDeadline = new Deadline("do homework",
                "Jan-12-2023 15:00", false);
        mockDeadline.setIsDone(true);
        assertEquals("[D][X] do homework (by: Jan-12-2023 15:00)",
                mockDeadline.toString());
    }

    @Test
    public void testUnmarkDeadline() {
        Deadline mockDeadline = new Deadline("do homework",
                "Jan-12-2023 15:00", true);
        mockDeadline.setIsDone(false);
        assertEquals("[D][ ] do homework (by: Jan-12-2023 15:00)",
                mockDeadline.toString());
    }
}
