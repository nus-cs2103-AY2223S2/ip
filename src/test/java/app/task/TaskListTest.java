package app.task;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void addTask_InvalidInputExceptionThrown() {
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            TaskList tl = new TaskList();
            Map<String, String> args = new HashMap<>();
            args.put("Command","deadline");
            args.put("Description", "it can be anything");
            args.put("from", "2023/01/27");
            // missing "to" -> <some valid date>
            tl.addDoneTask(TaskType.Type.EVENT, args);
        });

        assertEquals("eh need to specify ur event from when to when - event <desc> /from <when> /to <when>", thrown.getMessage());
    }

}
