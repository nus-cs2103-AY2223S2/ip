package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class TaskListTest {
    @Test 
    public void listTest() {
        TaskList temp = new TaskList();
        ArrayList<Task> tempList = new ArrayList<>();
        temp.setList(tempList);
        assertTrue(temp.getList().equals(tempList));
    }
}

