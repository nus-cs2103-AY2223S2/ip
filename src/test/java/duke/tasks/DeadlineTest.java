package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void deadlineTestUi(){
        Deadline deadline = new Deadline("read book", "2022-01-23 18:00", false);
        assertEquals("\t[D][ ] read book (by: 2022-01-23 18:00)", deadline.toString());
    }
}