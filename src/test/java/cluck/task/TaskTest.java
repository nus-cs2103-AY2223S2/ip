package cluck.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import cluck.exceptions.CorruptedDataException;
import cluck.tasks.Deadline;
import cluck.tasks.Task;
import cluck.tasks.ToDo;

public class TaskTest {

    private static final String VALID_DATE_TIME_1 = "03 12 2023 12";
    private static final String VALID_DATE_TIME_2 = "03 dec 23 1200";
    private static final String VALID_DATE_TIME_3 = "03 Dec 23 12";
    private static final String VALID_DATE_TIME_4 = "03 Jun 23 12";

    @Test
    public void todoFromSaveTest() throws Exception {
        String workingTodoSave = "T|0|Meet Jason";
        Task savedTask = Task.buildTaskFromSave(workingTodoSave);
        Task testTask = new ToDo("Meet Jason");
        assertEquals(savedTask, testTask);

    }

    @Test
    public void todoIncorrectSave() {
        String workingTodoSave = "T|X|Meet Jason";
        assertThrows(CorruptedDataException.class, ()
                -> Task.buildTaskFromSave(workingTodoSave));
    }

    @Test
    public void deadlineWithValidDateTime() {
        String description = "Assignment1";
        Deadline validDeadline1 = new Deadline(description, VALID_DATE_TIME_1);
        Deadline validDeadline2 = new Deadline(description, VALID_DATE_TIME_2);
        assertEquals(validDeadline1, validDeadline2);
        Deadline validDeadline3 = new Deadline(description, VALID_DATE_TIME_3);
        Deadline validDeadline4 = new Deadline(description, VALID_DATE_TIME_4);
        assertEquals(validDeadline3, validDeadline4);

    }
}
