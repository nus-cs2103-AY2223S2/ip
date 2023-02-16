package elise.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void test1() {
        ToDo actual = new ToDo(true, new String[] {"content"});
        assertEquals(actual.toString(), "content");
    }

    @Test
    public void test2() {
        ToDo actual = new ToDo(false, new String[] {"run around"});
        assertEquals(actual.fileMessage(), "T||0||run around\n");
    }
}
