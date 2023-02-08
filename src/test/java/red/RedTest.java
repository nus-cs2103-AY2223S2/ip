package red;

import org.junit.jupiter.api.Test;
import red.task.DeadlineTask;
import red.task.ToDoTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }

    @Test
    public void deadlineTaskTest() {
        DeadlineTask currentTask = new DeadlineTask("eat","8/12/2022");
        assertEquals("[D][ ] eat (Before: Dec 08 2022)", currentTask.toString());
    }

    @Test
    public void toDoTaskTest() {
        ToDoTask currentTask = new ToDoTask("eat");
        assertEquals("[T][ ] eat", currentTask.toString());
    }
}