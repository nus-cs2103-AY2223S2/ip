package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTaskTest {
    static final String SPACED_TASK_NAME = "deadline test";
    static final String VALID_DEADLINE = "2023-01-27";
    static final String CORRECT_TASK_OUTPUT = "[Deadline] [ ] deadline test (BY: Jan 27 2023)";

    @Test
    void createTask_normalInput_createdCorrectly() throws DukeException {
        DeadlineTask task = DeadlineTask.createTask(SPACED_TASK_NAME, VALID_DEADLINE);
        assertEquals(CORRECT_TASK_OUTPUT, task.toString());
    }
}
