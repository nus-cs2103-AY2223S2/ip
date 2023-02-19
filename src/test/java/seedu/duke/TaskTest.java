package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

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
