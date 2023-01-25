package duke;

import org.junit.jupiter.api.Test;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void addTaskCommand_toDoTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Valid task description"));
        assertEquals(1, taskList.size());
    }

    @Test
    public void deadlineCommand_invalidDateTime_exceptionThrown() {
        TaskList taskList = new TaskList();
        assertThrows(DateTimeParseException.class,
                () -> taskList.addTask(new Deadline("invalid hour", Parser.parseDate(
                        "22-01-2023 25:00", false))));
    }

    @Test
    public void eventCommand_invalidDateTime_exceptionThrown() {
        TaskList taskList = new TaskList();
        assertThrows(DateTimeParseException.class,
                () -> taskList.addTask(new Event("invalid start day", Parser.parseDate(
                        "36-01-2023 16:00", false),
                        Parser.parseDate("04-02-2023 12:00", false))));
    }
}
