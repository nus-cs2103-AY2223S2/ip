package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {

    @Test
    public void contains_addTodo_regexSuccessful() {
        HashMap<String, String> parsed = new HashMap<>();
        parsed.put("todo", "return book to library");
        try {
            Task task = new ToDo(parsed);
            assertEquals(task.contains("book"), true);
            assertEquals(task.contains("ary"), true);
            assertEquals(task.contains("book to lib"), true);
            assertEquals(task.contains("rary "), false);
            assertEquals(task.contains(" return"), false);
        } catch (DukeException e) {
            fail();
        }
    }
}
