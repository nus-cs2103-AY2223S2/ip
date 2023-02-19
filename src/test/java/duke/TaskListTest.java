package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

public class TaskListTest {
    @Test
    public void addTaskCommand_toDoTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Valid task description", 1));
        assertEquals(1, taskList.size());
    }

    @Test
    public void deadlineCommand_invalidDateTime_exceptionThrown() {
        TaskList taskList = new TaskList();
        assertThrows(DateTimeParseException.class, ()
            -> taskList.addTask(new Deadline("invalid hour", 1, Parser.parseDate(
                        "22-01-2023 25:00", false))));
    }

    @Test
    public void eventCommand_invalidDateTime_exceptionThrown() {
        TaskList taskList = new TaskList();
        assertThrows(DateTimeParseException.class, ()
            -> taskList.addTask(new Event("invalid start day", 2, Parser.parseDate(
                        "36-01-2023 16:00", false),
                        Parser.parseDate("04-02-2023 12:00", false))));
    }
}
