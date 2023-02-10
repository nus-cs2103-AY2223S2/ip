package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private Deadline deadlineTask = new Deadline("homework", "2023-10-10 2359");

    @Test
    public void saveDataFormatTest() {
        String expectedFormat = "D / 0 / homework / 2023-10-10 2359";
        assertEquals(expectedFormat, deadlineTask.getDataToSave());
    }

    @Test
    public void markAsDoneTest() {
        deadlineTask = deadlineTask.markAsDone();
        assertEquals("[D][X] homework (by: Oct 10 2023 23:59)", deadlineTask.toString());
    }
}
