package classes;

import exceptions.IncorrectNoOfArgumentException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Task tempTask = new Deadline("Assignment Submission", "2023-01-12", "12:30");
    Task tempTask2 = new Deadline("Assignment Submission", "2023-01-12", "");

    @Test
    public void getTaskInfoStatus() {
        tempTask.markAsDone();
        assertEquals("[D][X] Assignment Submission(by: January 12, 2023 | 12:30:00 PM)",
                tempTask.getTaskInfoStatus());
        System.out.println("Passed 1/2 checks of marking tasks: Deadline.getTaskInfoStatus()");
        tempTask2.markAsDone();
        assertEquals("[D][X] Assignment Submission(by: January 12, 2023)",
                tempTask2.getTaskInfoStatus());
        System.out.println("Passed 2/2 checks of marking tasks: Deadline.getTaskInfoStatus()");
        tempTask.markAsIncomplete();
        assertEquals("[D][ ] Assignment Submission(by: January 12, 2023 | 12:30:00 PM)",
                tempTask.getTaskInfoStatus());
        System.out.println("Passed 1/2 checks of unmarking tasks: Deadline.getTaskInfoStatus()");
        tempTask2.markAsIncomplete();
        assertEquals("[D][ ] Assignment Submission(by: January 12, 2023)",
                tempTask2.getTaskInfoStatus());
        System.out.println("Passed 2/2 checks of unmarking tasks: Deadline.getTaskInfoStatus()");
    }

    @Test
    public void getTaskInfo() {
        tempTask.markAsDone();
        assertEquals("[D][X] Assignment Submission/by 2023-01-12 12:30",
                tempTask.getTaskInfo());
        System.out.println("Passed 1/2 checks of marking tasks: Deadline.getTaskInfo()");
        tempTask2.markAsDone();
        assertEquals("[D][X] Assignment Submission/by 2023-01-12 ",
                tempTask2.getTaskInfo());
        System.out.println("Passed 2/2 checks of marking tasks: Deadline.getTaskInfo()");
        tempTask.markAsIncomplete();
        assertEquals("[D][ ] Assignment Submission/by 2023-01-12 12:30",
                tempTask.getTaskInfo());
        System.out.println("Passed 1/2 checks of unmarking tasks: Deadline.getTaskInfo()");
        tempTask2.markAsIncomplete();
        assertEquals("[D][ ] Assignment Submission/by 2023-01-12 ",
                tempTask2.getTaskInfo());
        System.out.println("Passed 2/2 checks of unmarking tasks: Deadline.getTaskInfo()");
    }
}
