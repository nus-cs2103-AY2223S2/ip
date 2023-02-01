package duke;

import exceptions.IncorrectNoOfArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Task tempTask = new Event("Birthday", "2023-02-05", "2023-02-05", "00:00", "23:59");
    Task tempTask2 = new Event("Birthday", "2023-02-05", "2023-02-05", "", "23:59");
    Task tempTask3 = new Event("Birthday", "2023-02-05", "2023-02-05", "00:00", "");
    Task tempTask4 = new Event("Birthday", "2023-02-05", "2023-02-05", "", "");

    @Test
    public void getTaskInfoStatus() {
        tempTask.setDone();
        assertEquals("[E][X] Birthday(from: February 5, 2023 | 12:00:00 AM to: February 5, 2023 | 11:59:00 PM)",
                tempTask.getTaskInfoStatus());
        System.out.println("Passed 1/4 checks of marking tasks: Event.getTaskInfoStatus()");
        tempTask2.setDone();
        assertEquals("[E][X] Birthday(from: February 5, 2023 to: February 5, 2023 | 11:59:00 PM)",
                tempTask2.getTaskInfoStatus());
        System.out.println("Passed 2/4 checks of marking tasks: Event.getTaskInfoStatus()");
        tempTask3.setDone();
        assertEquals("[E][X] Birthday(from: February 5, 2023 | 12:00:00 AM to: February 5, 2023)",
                tempTask3.getTaskInfoStatus());
        System.out.println("Passed 3/4 checks of marking tasks: Event.getTaskInfoStatus()");
        tempTask4.setDone();
        assertEquals("[E][X] Birthday(from: February 5, 2023 to: February 5, 2023)",
                tempTask4.getTaskInfoStatus());
        System.out.println("Passed 4/4 checks of marking tasks: Event.getTaskInfoStatus()");
        tempTask.setIncomplete();
        assertEquals("[E][ ] Birthday(from: February 5, 2023 | 12:00:00 AM to: February 5, 2023 | 11:59:00 PM)",
                tempTask.getTaskInfoStatus());
        System.out.println("Passed 1/4 checks of unmarking tasks: Event.getTaskInfoStatus()");
        tempTask2.setIncomplete();
        assertEquals("[E][ ] Birthday(from: February 5, 2023 to: February 5, 2023 | 11:59:00 PM)",
                tempTask2.getTaskInfoStatus());
        System.out.println("Passed 2/4 checks of unmarking tasks: Event.getTaskInfoStatus()");
        tempTask3.setIncomplete();
        assertEquals("[E][ ] Birthday(from: February 5, 2023 | 12:00:00 AM to: February 5, 2023)",
                tempTask3.getTaskInfoStatus());
        System.out.println("Passed 3/4 checks of unmarking tasks: Event.getTaskInfoStatus()");
        tempTask4.setIncomplete();
        assertEquals("[E][ ] Birthday(from: February 5, 2023 to: February 5, 2023)",
                tempTask4.getTaskInfoStatus());
        System.out.println("Passed 4/4 checks of unmarking tasks: Event.getTaskInfoStatus()");
    }

    @Test
    public void getTaskInfo() {
        tempTask.setDone();
        assertEquals("[E][X] Birthday/from 2023-02-05 00:00 /to 2023-02-05 23:59",
                tempTask.getTaskInfo());
        System.out.println("Passed 1/4 checks of marking tasks: Event.getTaskInfo()");
        tempTask2.setDone();
        assertEquals("[E][X] Birthday/from 2023-02-05 /to 2023-02-05 23:59",
                tempTask2.getTaskInfo());
        System.out.println("Passed 2/4 checks of marking tasks: Event.getTaskInfo()");
        tempTask3.setDone();
        assertEquals("[E][X] Birthday/from 2023-02-05 00:00 /to 2023-02-05",
                tempTask3.getTaskInfo());
        System.out.println("Passed 3/4 checks of marking tasks: Event.getTaskInfo()");
        tempTask4.setDone();
        assertEquals("[E][X] Birthday/from 2023-02-05 /to 2023-02-05",
                tempTask4.getTaskInfo());
        System.out.println("Passed 4/4 checks of marking tasks: Event.getTaskInfo()");
        tempTask.setIncomplete();
        assertEquals("[E][ ] Birthday/from 2023-02-05 00:00 /to 2023-02-05 23:59",
                tempTask.getTaskInfo());
        System.out.println("Passed 1/4 checks of unmarking tasks: Event.getTaskInfo()");
        tempTask2.setIncomplete();
        assertEquals("[E][ ] Birthday/from 2023-02-05 /to 2023-02-05 23:59",
                tempTask2.getTaskInfo());
        System.out.println("Passed 2/4 checks of unmarking tasks: Event.getTaskInfo()");
        tempTask3.setIncomplete();
        assertEquals("[E][ ] Birthday/from 2023-02-05 00:00 /to 2023-02-05",
                tempTask3.getTaskInfo());
        System.out.println("Passed 3/4 checks of unmarking tasks: Event.getTaskInfo()");
        tempTask4.setIncomplete();
        assertEquals("[E][ ] Birthday/from 2023-02-05 /to 2023-02-05",
                tempTask4.getTaskInfo());
        System.out.println("Passed 4/4 checks of unmarking tasks: Event.getTaskInfo()");
    }
}