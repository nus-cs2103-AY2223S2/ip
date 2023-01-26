package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest1(){
        Task task = new Todo("return book");
        assertEquals("[T][ ] return book", task.toString());
    }

    @Test
    public void toStringTest2(){
        Task task = new Todo("read book");
        task.markAsDone();
        assertEquals("[T][X] read book", task.toString());
        task.markAsUndone();
        assertEquals("[T][ ] read book", task.toString());
    }
}
