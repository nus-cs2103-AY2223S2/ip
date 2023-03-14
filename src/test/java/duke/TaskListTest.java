package duke;

import Duke.Exception.InvalidCommandException;
import Duke.Exception.NoDescriptionException;
import org.junit.jupiter.api.Test;
import Duke.TaskList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void Test1(){

        try {
            TaskList.add_to_list("todo borrow book");
        }
        catch(InvalidCommandException e) {
            System.out.println("the command is invalid");
        }
        catch(NoDescriptionException e) {
            System.out.println("the task needs to have a description");
        }

        assertEquals(1, TaskList.list.size());
    }

}
