package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

import java.time.LocalDate;

/**
 * The type Task list test.
 */
public class TaskListTest {

    /**
     * Executes Add and delete test for TaskList.
     *
     * @throws DukeException the duke exception
     */
    @Test
    public void addAndDeleteTest() throws DukeException {
        TaskList testTaskList = new TaskList();
        testTaskList.addTodo("do testing");
        testTaskList.addDeadline("do hw ", "2023-02-02");
        int expectedSize = testTaskList.size();
        int actualSize = 2;
        assertEquals(expectedSize, actualSize);
        assertEquals(testTaskList.delete(0), String.format("OK, I've deleted: %s\n\n%s",
                new Todo("do testing"), testTaskList.showList()));
    }

    /**
     * Executes Find test for TaskList
     *
     * @throws DukeException the duke exception
     */
    @Test
    public void findTest() throws DukeException {
        TaskList testTaskList = new TaskList();
        testTaskList.addTodo("do testing");
        testTaskList.addDeadline("do hw ", "2023-02-02");
        assertEquals(testTaskList.find("hw"), String.format("Here's what I found:\n%s%s\n",
                "2.", new Deadline("do hw ", LocalDate.parse("2023-02-02"))));

    }
}
