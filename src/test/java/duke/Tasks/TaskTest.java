package duke.Tasks;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;

public class TaskTest {
    @Test
    public void testGetDescription() {

        assertAll(() -> {
            Task task = new Task("study", false);
            assertEquals("study",
                    task.getDescription());
        });
    }

}
