import org.junit.jupiter.api.Test;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTest1() {
        Todo testDead = new Todo("asdf");
        testDead.setChecked(true);
        assertEquals(testDead.toString(), "[T] [X] asdf");
    }
}
