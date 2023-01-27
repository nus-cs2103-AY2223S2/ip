package core;

import exceptions.invalid.Index;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskMasterTest {

    @Test
    void list_normal_success() {
        TaskMaster testcase = new TaskMaster();
        assertEquals("Oh my, the list is empty!\n", testcase.list());

        testcase.addToDo("Homework", false);
        assertEquals("1.[T][ ] Homework\n", testcase.list());
    }

    @Test
    void markComplete_normal_success() {
        TaskMaster testcase = new TaskMaster();
        testcase.addToDo("Homework", false);
        try {
            assertEquals("Nice! I've marked this task as done:\n" +
                    "[T][X] Homework", testcase.markComplete(0,true));
        } catch (Index e) {
            throw new RuntimeException(e);
        }
        assertEquals("1.[T][X] Homework\n", testcase.list());
    }

    @Test
    void markComplete_normal_failure() {
        TaskMaster testcase = new TaskMaster();
        try {
            testcase.markComplete(9,true);
        } catch (Index e) {
            assertEquals("OOPS!!! The list does not contain index 10", e.getMessage());
//            throw new RuntimeException(e);
        }
    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void addToDo() {
//    }
//
//    @Test
//    void addDeadLine() {
//    }
//
//    @Test
//    void addEvent() {
//    }
//
//    @Test
//    void export() {
//    }
}