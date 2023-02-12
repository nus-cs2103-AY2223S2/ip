package duke.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {

    @Test
    public void deadlineTask_noDate_exceptionThrown() {
        assertThrows(AssertionError.class, () -> new DeadlineTask(
                "no date",
                null
        ));
    }
}
