package tasks;

import treebot.exception.TreeBotException;
import org.junit.jupiter.api.Test;
import treebot.tasks.TaskFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskFactoryTest {

//    @Test
//    void makeTodo_emptyTaskDescription_exceptionThrown() {
//        Exception e = assertThrows(TreeBotException.class, () -> new TaskFactory().make("todo", ""));
//        assertEquals("Todo task parameter cannot be empty", e.getMessage());
//    }
//
//    @Test
//    void makeDeadline_invalidDateFormat_exceptionThrown() {
//        Exception e1 = assertThrows(TreeBotException.class, () -> new TaskFactory().make("deadline", "do homework /by 2/12/2019"));
//        Exception e2 = assertThrows(TreeBotException.class, () -> new TaskFactory().make("deadline", "do homework /by 2/12/2019 182"));
//        Exception e3 = assertThrows(TreeBotException.class, () -> new TaskFactory().make("deadline", "do homework /by 2 dec 2019"));
//
//        assertEquals(e1.getMessage(), "Invalid date time format for deadline");
//        assertEquals(e2.getMessage(), "Invalid date time format for deadline");
//        assertEquals(e3.getMessage(), "Invalid date time format for deadline");
//
//    }

}
