package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.invalid.Index;
import exceptions.invalid.Input;

class TaskMasterTest {

    @Test
    void list_normal_success() {
        TaskMaster testcase = new TaskMaster();
        assertEquals("Oh my, the list is empty!\n", testcase.listAllTasks());

        testcase.addToDo("homework", false);
        assertEquals("1.[T][ ] homework\n", testcase.listAllTasks());

        try {
            assertEquals("Got it. I've added this task:\n"
                    + "[D][ ] Homework (by: 10/05/2022 10:00)\n"
                    + "Now you have 2 tasks in the list.\n",
                    testcase.addDeadLine("Homework", false,
                    DateHandler.parse("2022/05/10 1000")));
        } catch (Input e) {
            throw new RuntimeException("Unexpected results from TaskMasterTest.");
        }
    }

    @Test
    void markComplete_normal_success() {
        TaskMaster testcase = new TaskMaster();
        testcase.addToDo("Homework", false);
        try {
            assertEquals("Nice! I've marked this task as done:\n"
                    + "[T][X] Homework", testcase.markComplete(0, true));
        } catch (Index e) {
            throw new RuntimeException(e);
        }
        assertEquals("1.[T][X] Homework\n", testcase.listAllTasks());
    }

    @Test
    void markComplete_normal_failure() {
        TaskMaster testcase = new TaskMaster();
        try {
            testcase.markComplete(9, true);
        } catch (Index e) {
            assertEquals("OOPS!!! The list does not contain item at index 10", e.getMessage());
        }

        try {
            testcase.addDeadLine("Homework", false, DateHandler.parse("1000/99/99 2500"));
        } catch (Input e) {
            assertEquals("OOPS!!! Incorrect date time format! Should be 'yyyy/MM/dd HHmm'", e.getMessage());
        }
    }

    @Test
    void deleteTask_normal_success() {
        TaskMaster testcase = new TaskMaster();

        testcase.addToDo("homework", false);
        assertEquals("1.[T][ ] homework\n", testcase.listAllTasks());

        try {
            assertEquals("Noted. I've removed this task:\n"
                            + "[T][ ] homework",
                    testcase.deleteTask(0));

        } catch (Index e) {
            throw new RuntimeException(e);
            //            "OOPS!!! The list does not contain item at index 1"
        }
    }

    @Test
    void deleteTask_normal_failure() {
        TaskMaster testcase = new TaskMaster();

        testcase.addToDo("homework", false);
        assertEquals("1.[T][ ] homework\n", testcase.listAllTasks());

        try {
            assertEquals("Noted. I've removed this task:\n"
                            + "[T][ ] homework",
                    testcase.deleteTask(99));

        } catch (Index e) {
            assertEquals("OOPS!!! The list does not contain item at index 99", e.getMessage());
        }
    }

    @Test
    void findTask_normal_success() {
        TaskMaster testcase = new TaskMaster();

        testcase.addToDo("homework", false);
        assertEquals("", testcase.findTask("DOES NOT EXIST"));
        assertEquals("2.[T][ ] homework\n", testcase.findTask("work"));
        testcase.addToDo("workshop", false);
        assertEquals("2.[T][ ] homework\n" + "3.[T][ ] workshop\n", testcase.findTask("work"));
    }
}
