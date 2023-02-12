package duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {

    @Test
    public void todoTask_normalFormat_createsTask() {
        assertDoesNotThrow(() -> new TodoTask(
                "Good format"
        ));
    }
}
