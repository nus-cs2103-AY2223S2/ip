package duke;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.enums.Views;
import duke.task.Todo;

public class TaskListTest {

    @Test
    public void testRemoveEmptyStrings() {
        String[] expected = { "test" };
        String[] actual = TaskList.removeEmptyStrings("", "test");
        Assertions.assertArrayEquals(expected, actual);
        // Test no empty string
        actual = TaskList.removeEmptyStrings("test");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testTaskListSize() throws DukeException {
        int expected = 0;
        TaskList list = new TaskList();
        int actual = list.size();
        Assertions.assertEquals(expected, actual);
        list.add(new Todo("test"));
        expected = 1;
        actual = list.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testTaskListOutOfBoundFail() {
        try {
            new TaskList().get(0);
        } catch (DukeException e) {
            // Check if duke message is expected
            String expected = Views.OUT_RANGE_ERR_STRING.str();
            Assertions.assertEquals(expected, e.getMessage());
            // Assertion failed, as expected
            return;
        }
        // If the code above didn't throw an DukeException, this line will be reached
        fail("Expected an DukeException to be thrown");
    }

}
