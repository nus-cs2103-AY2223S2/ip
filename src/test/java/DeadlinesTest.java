import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {
    //test setup
    Deadlines task = new Deadlines("Do Homework", "2023-01-28T23:59:59");
    @Test
    public void getBy_ValidDateTime_DeadlineCreated(){
        assertEquals("2023-01-28T23:59:59", task.getBy());
    }

    @Test
    public void getDeadline_ValidDateTime_StringReturned(){
        assertEquals("[D][0] Do Homework(by: 28-01-2023 23:59:59)", task.toString());
    }
}
