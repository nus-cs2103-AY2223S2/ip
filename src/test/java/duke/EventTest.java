package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Represents the test class for Event.java.
 *
 * @author MrTwit99
 * @since 2023-02-12
 */
public class EventTest {
    private Task tempTask = new Event("Birthday", "2023-02-05", "2023-02-05",
            "00:00", "23:59");
    private Task tempTask2 = new Event("Birthday", "2023-02-05", "2023-02-05",
            "", "23:59");
    private Task tempTask3 = new Event("Birthday", "2023-02-05", "2023-02-05",
            "00:00", "");
    private Task tempTask4 = new Event("Birthday", "2023-02-05", "2023-02-05",
            "", "");

    /**
     * Tests the method getTaskInfoStatus() in Event.java when an Event is set as completed.
     */
    @Test
    public void getTaskInfoStatus() {
        tempTask.setDone();
        assertEquals("[E][X] Birthday(from: February 5, 2023 | 12:00:00 AM to: February 5, 2023 | 11:59:00 PM)",
                tempTask.getTaskInfoStatus());
        tempTask2.setDone();
        assertEquals("[E][X] Birthday(from: February 5, 2023 to: February 5, 2023 | 11:59:00 PM)",
                tempTask2.getTaskInfoStatus());
        tempTask3.setDone();
        assertEquals("[E][X] Birthday(from: February 5, 2023 | 12:00:00 AM to: February 5, 2023)",
                tempTask3.getTaskInfoStatus());
        tempTask4.setDone();
        assertEquals("[E][X] Birthday(from: February 5, 2023 to: February 5, 2023)",
                tempTask4.getTaskInfoStatus());
    }

    /**
     * Tests the method getTaskInfoStatus() in Event.java when an Event is set as incomplete.
     */
    @Test
    public void getTaskInfoStatus2() {
        tempTask.setIncomplete();
        assertEquals("[E][ ] Birthday(from: February 5, 2023 | 12:00:00 AM to: February 5, 2023 | 11:59:00 PM)",
                tempTask.getTaskInfoStatus());
        tempTask2.setIncomplete();
        assertEquals("[E][ ] Birthday(from: February 5, 2023 to: February 5, 2023 | 11:59:00 PM)",
                tempTask2.getTaskInfoStatus());
        tempTask3.setIncomplete();
        assertEquals("[E][ ] Birthday(from: February 5, 2023 | 12:00:00 AM to: February 5, 2023)",
                tempTask3.getTaskInfoStatus());
        tempTask4.setIncomplete();
        assertEquals("[E][ ] Birthday(from: February 5, 2023 to: February 5, 2023)",
                tempTask4.getTaskInfoStatus());
    }

    /**
     * Tests the method getTaskInfo() in Event.java when an Event is set as completed.
     */
    @Test
    public void getTaskInfo() {
        tempTask.setDone();
        assertEquals("[E][X] Birthday/from 2023-02-05 00:00 /to 2023-02-05 23:59",
                tempTask.getTaskInfo());
        tempTask2.setDone();
        assertEquals("[E][X] Birthday/from 2023-02-05 /to 2023-02-05 23:59",
                tempTask2.getTaskInfo());
        tempTask3.setDone();
        assertEquals("[E][X] Birthday/from 2023-02-05 00:00 /to 2023-02-05",
                tempTask3.getTaskInfo());
        tempTask4.setDone();
        assertEquals("[E][X] Birthday/from 2023-02-05 /to 2023-02-05",
                tempTask4.getTaskInfo());
    }

    /**
     * Tests the method getTaskInfo() in Event.java when an Event is set as incomplete.
     */
    @Test
    public void getTaskInfo2() {
        tempTask.setIncomplete();
        assertEquals("[E][ ] Birthday/from 2023-02-05 00:00 /to 2023-02-05 23:59",
                tempTask.getTaskInfo());
        tempTask2.setIncomplete();
        assertEquals("[E][ ] Birthday/from 2023-02-05 /to 2023-02-05 23:59",
                tempTask2.getTaskInfo());
        tempTask3.setIncomplete();
        assertEquals("[E][ ] Birthday/from 2023-02-05 00:00 /to 2023-02-05",
                tempTask3.getTaskInfo());
        tempTask4.setIncomplete();
        assertEquals("[E][ ] Birthday/from 2023-02-05 /to 2023-02-05",
                tempTask4.getTaskInfo());
    }
}
