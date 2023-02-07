package jane.test;
import jane.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Todo todo = new Todo(1,"run");
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }
}