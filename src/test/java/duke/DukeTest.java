package duke;

import org.junit.jupiter.api.Test;
import task.TaskList;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }

    @Test
    public void test1() throws DukeException {
        TaskList taskList = new TaskList(new LinkedList<>());
        assertEquals("you", taskList.removeKeyword("Hello you"));
    }

    @Test
    public void test2() {
        TaskList taskList = new TaskList(new LinkedList<>());
        assertThrows(DukeException.class, ()-> {
            taskList.removeKeyword("");
        });
    }

}
