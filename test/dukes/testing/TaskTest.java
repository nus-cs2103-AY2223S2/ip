package dukes.testing;

import dukes.engine.*;
import dukes.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getTag() {
        Task newTask = new Task("trial");
        assertEquals("", newTask.getTag());
    }

    @Test
    void testToString() {
        Task newTask = new Task("trial");
        assertEquals("[ ] trial", newTask.toString());
    }
}