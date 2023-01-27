package handlers;

import containers.FileContainer;
import org.junit.jupiter.api.Test;
import services.TaskList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JMarkTaskTest {
    @Test
    public void patternTest1() {
        assertTrue(new JMarkTask(new TaskList(FileContainer.ofLocation("NUL", true))).canTake("unmark 12345"));
    }
}
