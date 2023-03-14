package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        try {
            Deadline d = new Deadline("homework", "2024-01-20 1400");
            assertEquals("[D] [   ]  homework\n (by: Jan 20 2024 02:00 PM)\n", d.toString());
        } catch (DukeException e) {
            e.getMessage();
        }
    }
}
