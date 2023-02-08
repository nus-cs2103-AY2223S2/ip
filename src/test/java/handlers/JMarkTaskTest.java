package handlers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import containers.FileContainer;
import services.TaskList;

public class JMarkTaskTest {
    @Test
    public void patternTest1() {
        assertTrue(new JMarkTask(new TaskList(FileContainer.ofLocation("NUL", true))).canTake("unmark 12345"));
    }
}
