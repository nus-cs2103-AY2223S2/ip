package jane.test;
import jane.task.Todo;
import jane.task.Deadline;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import static java.util.Calendar.APRIL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Todo todo = new Todo(1,"run");
    LocalDateTime testDate = LocalDateTime.of(2017, Month.APRIL ,6, 10, 30);
    Deadline d = new Deadline(2, "meeting", testDate );
    @Test
    public void todoTestName() {
        assertEquals(todo.description, "run");
    }
    @Test
    public void deadlineTestName() {
        assertEquals(d.description, "meeting");
    }
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }
}