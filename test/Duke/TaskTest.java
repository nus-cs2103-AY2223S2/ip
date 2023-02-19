package Duke;

import Duke.Tasks.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
    @Test
    public void defaultDoneTest() {
        assertEquals(false, new ToDo("homework").isDone());
    }
    @Test
    public void outputTest() {
        assertEquals("[T] [ ] homework", new ToDo("homework").toString());
    }
}