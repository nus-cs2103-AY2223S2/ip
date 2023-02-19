package berry.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import berry.exception.IncorrectDateException;

public class TaskTest {
    @Test
    public void markAndStatus_markAndUnmarkAll_correctStatus() throws IncorrectDateException {
        final String marked = "X";
        final String unmarked = " ";
        final String someDate = "2022-02-02";

        Todo todoTask = new Todo("some todo");
        Deadline deadlineTask = new Deadline("some deadline", someDate);
        Event eventTask = new Event("some event", someDate, someDate);

        todoTask.markTaskAsDone();
        deadlineTask.markTaskAsDone();
        eventTask.markTaskAsDone();

        assertEquals(marked, todoTask.getStatusIcon());
        assertEquals(marked, deadlineTask.getStatusIcon());
        assertEquals(marked, eventTask.getStatusIcon());

        todoTask.markTaskAsNotDone();
        deadlineTask.markTaskAsNotDone();
        eventTask.markTaskAsNotDone();

        assertEquals(unmarked, todoTask.getStatusIcon());
        assertEquals(unmarked, deadlineTask.getStatusIcon());
        assertEquals(unmarked, eventTask.getStatusIcon());
    }
}
