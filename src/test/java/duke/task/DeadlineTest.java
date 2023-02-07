package duke.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Deadline deadlineTask;

    @BeforeEach
    public void setUp() {
        deadlineTask = new Deadline("deadline return book /by 2020-10-10",
                LocalDate.parse("2020-10-10"));
    }

    @Test
    public void getTaskTest() {
        assertEquals("return book", deadlineTask.getTask());
    }

    @Test
    public void getDeadlineTest() {
        assertEquals( "Oct 10 2020", deadlineTask.getDeadline());
    }

    @Test
    public void markTaskTest() {
        deadlineTask.markTask();
        assertEquals("[D][X] return book ( by: Oct 10 2020 )",deadlineTask.toString());
    }

    @Test
    public void unmarkTaskTest() {
        deadlineTask.markTask();
        deadlineTask.unmarkTask();
        assertEquals("[D][ ] return book ( by: Oct 10 2020 )",deadlineTask.toString());
    }
}
