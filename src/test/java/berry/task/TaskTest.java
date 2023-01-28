package berry.task;

import berry.exception.IncorrectDateException;
import berry.task.Deadline;
import berry.task.Todo;
import berry.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void markAndStatus_markAndUnmarkAll_correctStatus() throws IncorrectDateException {
        final String marked = "X";
        final String unmarked = " ";
        final String someDate = "2022-02-02";

        Todo todoTask = new Todo("some todo");
        Deadline deadlineTask = new Deadline("some deadline", someDate);
        Event eventTask = new Event("some event", someDate, someDate);

        todoTask.markAsDone();
        deadlineTask.markAsDone();
        eventTask.markAsDone();

        assertEquals(marked, todoTask.getStatusIcon());
        assertEquals(marked, deadlineTask.getStatusIcon());
        assertEquals(marked, eventTask.getStatusIcon());

        todoTask.markAsNotDone();
        deadlineTask.markAsNotDone();
        eventTask.markAsNotDone();

        assertEquals(unmarked, todoTask.getStatusIcon());
        assertEquals(unmarked, deadlineTask.getStatusIcon());
        assertEquals(unmarked, eventTask.getStatusIcon());
    }
}
