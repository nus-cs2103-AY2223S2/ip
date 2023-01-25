package connor.task;

import connor.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskTest {

    @Test
    public void getMarkTest() {
        Todo test1 = new Todo("sleep");
        assertEquals(test1.getMark(), "[ ]");
        test1.mark();
        assertEquals(test1.getMark(), "[X]");
    }

    @
}
