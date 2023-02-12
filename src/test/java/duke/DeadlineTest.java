package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents the test class for Deadline.java.
 *
 * @author MrTwit99
 * @since 2023-02-12
 */
public class DeadlineTest {
    Task tempTask = new Deadline("Assignment Submission", "2023-01-12", "12:30");
    Task tempTask2 = new Deadline("Assignment Submission", "2023-01-12", "");

    /**
     * Tests the method getTaskInfoStatus() in Deadline.java.
     */
    @Test
    public void getTaskInfoStatus() {
        tempTask.setDone();
        assertEquals("[D][X] Assignment Submission(by: January 12, 2023 | 12:30:00 PM)",
                tempTask.getTaskInfoStatus());
        tempTask2.setDone();
        assertEquals("[D][X] Assignment Submission(by: January 12, 2023)",
                tempTask2.getTaskInfoStatus());
        tempTask.setIncomplete();
        assertEquals("[D][ ] Assignment Submission(by: January 12, 2023 | 12:30:00 PM)",
                tempTask.getTaskInfoStatus());
        tempTask2.setIncomplete();
        assertEquals("[D][ ] Assignment Submission(by: January 12, 2023)",
                tempTask2.getTaskInfoStatus());
    }

    /**
     * Tests the method getTaskInfo() in Deadline.java.
     */
    @Test
    public void getTaskInfo() {
        tempTask.setDone();
        assertEquals("[D][X] Assignment Submission/by 2023-01-12 12:30",
                tempTask.getTaskInfo());
        tempTask2.setDone();
        assertEquals("[D][X] Assignment Submission/by 2023-01-12 ",
                tempTask2.getTaskInfo());
        tempTask.setIncomplete();
        assertEquals("[D][ ] Assignment Submission/by 2023-01-12 12:30",
                tempTask.getTaskInfo());
        tempTask2.setIncomplete();
        assertEquals("[D][ ] Assignment Submission/by 2023-01-12 ",
                tempTask2.getTaskInfo());
    }
}
