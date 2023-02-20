package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Contains tests for TaskList class.
 * The following methods are expected to work since they are wrapper functions for ArrayList:
 *     add(Task)
 *     size()
 *     isEmpty()
 */
public class TaskListTest {

    private static final int DUMMY_TASKS_COUNT = 7;

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();

        for (int i = 0; i < DUMMY_TASKS_COUNT; i++) {
            taskList.add(new DummyTask());
        }
        assertEquals(DUMMY_TASKS_COUNT, taskList.size());
    }

    @Test
    void remove_invalidIndex() {
        assertNull(taskList.remove(-1));
        assertNull(taskList.remove(DUMMY_TASKS_COUNT));
    }

    @Test
    void remove_validIndex() {
        Task removedTask = taskList.remove(DUMMY_TASKS_COUNT - 1);

        assertNotNull(removedTask);
        assertEquals(DUMMY_TASKS_COUNT - 1, taskList.size());
    }

    @Test
    void removeAllTask() {
        taskList.removeAllTask();
        assertTrue(taskList.isEmpty());
    }

    @Test
    void isValidIndex_validIndex() {
        assertTrue(taskList.isValidIndex(DUMMY_TASKS_COUNT / 2));
    }

    @Test
    void isValidIndex_invalidIndex() {
        assertFalse(taskList.isValidIndex(-1));
        assertFalse(taskList.isValidIndex(DUMMY_TASKS_COUNT));
    }

    @Test
    void get_invalidIndex() {
        assertNull(taskList.get(-1));
        assertNull(taskList.get(DUMMY_TASKS_COUNT));
    }

    @Test
    void get_validIndex() {
        assertNotNull(taskList.get(DUMMY_TASKS_COUNT / 2));
    }
}
