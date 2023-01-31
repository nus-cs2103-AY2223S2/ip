package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {
    @Test
    public void firstTest() {
        DeadlineTask tempTask = new DeadlineTask("Do work", LocalDate.parse("2024-01-01"), true);
        assertEquals("[D] [X] Do work (by: 01 Jan 2024)", tempTask.toString());
    }

    @Test
    public void secondTest() {
        DeadlineTask tempTask = new DeadlineTask("Go home", LocalDate.parse("2024-03-03"), false);
        assertEquals("[D] [ ] Go home (by: 03 Mar 2024)", tempTask.toString());
    }
}
