package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void toStringTest(){
        Task t1 = new Task("read book");
        String result = "[ ] read book";
        assertEquals(t1.toString(), result);
    }
}
