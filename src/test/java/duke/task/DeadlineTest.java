package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeadlineTest {
    @DisplayName("Deadline toString Test")
    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("test", "2022-01-01");
        assertEquals("[D][ ] test (by: Jan 1 2022)", deadline.toString());
    }

    @DisplayName("Deadline (un)marking Test")
    @Test
    public void markUnmarkTest() {
        Deadline deadline = new Deadline("test", "2022-01-01");
        deadline.markTask();
        assertEquals("[D][X] test (by: Jan 1 2022)", deadline.toString());

        deadline.unmarkTask();
        assertEquals("[D][ ] test (by: Jan 1 2022)", deadline.toString());
    }

    @DisplayName("Deadline Export Test")
    @Test
    public void exportDataTest() {
        Deadline deadline = new Deadline("test", "2022-01-01");
        assertEquals("Deadline | marked: 0 ; description: test ; deadline: 2022-01-01", deadline.toData());

        deadline.markTask();
        assertEquals("Deadline | marked: 1 ; description: test ; deadline: 2022-01-01", deadline.toData());
    }
}
