package duke.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void task_noDescription_assertionError() {
        assertThrows(AssertionError.class, () -> new Task(
                null,
                false
        ));
    }
}
