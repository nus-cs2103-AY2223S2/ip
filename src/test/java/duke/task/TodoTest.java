package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void test1() {
        Todo generate = new Todo("read a book");
        String correct = "[T]" + "[ ] " + "read a book";
        assertEquals(correct, generate.toString());
    }

    @Test
    public void test2() {
        Todo generate = new Todo("read a book");
        assertEquals(false, generate.getStatus());
    }

    @Test
    public void test3() {
        Todo generate = new Todo("read a book");
        generate.taskDone();
        assertEquals(true, generate.getStatus());
    }
}
