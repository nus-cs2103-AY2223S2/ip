package app.task;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    private static final String ERROR_MSG_CHECK = "Plz provide BOTH the 'from' and 'to' in "
            + "yyyy-MM-dd HHmm or yyyy/MM/dd HHmm format.";
    @Test
    public void addTask_InvalidInputExceptionThrown() {
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            TaskList tl = new TaskList();
            Map<String, String> args = new HashMap<>();
            args.put("Command","deadline");
            args.put("description", "it can be anything");
            args.put("from", "2023/01/27");
            // missing "to" -> <some valid date>
            tl.addDoneTask(TaskTypes.Type.EVENT, args);
        });

        assertEquals(ERROR_MSG_CHECK, thrown.getMessage());
    }

}
