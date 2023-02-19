package berry.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import berry.exception.IncorrectDateException;

public class TaskListTest {
    private TaskList tl;

    @BeforeEach
    public void setUp() {
        tl = new TaskList();
    }

    @Test
    public void addTask_correctArgs_addCorrectly() throws IncorrectDateException {
        final String validDate = "2022-02-02";
        final Todo todo = new Todo("some todo");
        final Deadline deadline = new Deadline("some deadline", validDate);
        final Event event = new Event("some event", validDate, validDate);
        final Task[] taskList = {todo, deadline, event};

        addMultipleTasks(taskList);

        assertEquals(3, tl.getList().size());
    }

    @Test
    public void isIndexWithinRange_withinAndOutOfRange_checkCorrectly() throws IncorrectDateException {
        final String validDate = "2022-02-02";
        final Todo todo = new Todo("some todo");
        final Deadline deadline = new Deadline("some deadline", validDate);
        final Event event = new Event("some event", validDate, validDate);
        final Task[] taskList = {todo, deadline, event};

        int[] indexesWithinRange = {1, 2, 3};
        int[] indexesOutOfRange = {-3, 0, 4, 50};
        addMultipleTasks(taskList);

        /* Check for indexes within range of 1 to 3 */
        for (int index : indexesWithinRange) {
            /* Check indexesWithinRange */
            assertTrue(tl.isIndexWithinRange(index));
        }

        /* Check for indexes out of range of 1 to 3 */
        for (int index : indexesOutOfRange) {
            /* Check indexesOutOfRange */
            assertFalse(tl.isIndexWithinRange(index));
        }
    }

    /*
     * Utility methods ====================================================================================
     */

    /**
     * Adds each task from the given list into Tasklist tl
     *
     * @param taskList is the list of tasks to be added into TaskList tl
     */
    private void addMultipleTasks(Task[] taskList) {
        for (Task task : taskList) {
            tl.addTask(task);
        }
    }

}
