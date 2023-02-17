package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DeadlineTaskTest {
    @Test
    public void testConstruction_withValidInputs_expectedAttributes() {
        LocalDateTime deadline = LocalDateTime.of(2022, 12, 31, 23, 59);
        DeadlineTask task = new DeadlineTask("Finish project", deadline);
        assertEquals("Finish project", task.getInformation());
        assertEquals(deadline, task.getEndDate());
        assertEquals(TaskType.DEADLINE, task.getType());
        assertFalse(task.getStatus());
    }

    @Test
    public void testMatchesDate_withValidDate_expectedOutcome() {
        LocalDateTime deadline = LocalDateTime.of(2022, 12, 31, 23, 59);
        DeadlineTask task = new DeadlineTask("Finish project", deadline);

        assertTrue(task.matchesDate(LocalDate.of(2022, 12, 31)));
        assertFalse(task.matchesDate(LocalDate.of(2022, 12, 30)));
    }

    @Test
    public void testToString_withValidInputs_expectedOutcome() {
        LocalDateTime deadline = LocalDateTime.of(2022, 12, 31, 23, 59);
        DeadlineTask task = new DeadlineTask("Finish project", deadline);

        String expectedString = "[D][ ] Finish project ( by: Dec 31 2022 23:59 )";
        assertEquals(expectedString, task.toString());
    }

    @Test
    public void testStorageString_withValidInputs_expectedOutcome() {
        LocalDateTime deadline = LocalDateTime.of(2022, 12, 31, 23, 59);
        DeadlineTask task = new DeadlineTask("Finish project", deadline);

        String expectedString = "[D] | [ ] | Finish project | Dec 31 2022 23:59";
        assertEquals(expectedString, task.storageString());
    }

    @Test
    public void matchesDate_matchingDateBeforeDeadline_returnsTrue() {
        LocalDateTime deadline = LocalDateTime.of(2022, 3, 1, 12, 0);
        DeadlineTask task = new DeadlineTask("Task1", deadline);
        LocalDate dateToCheck = LocalDate.of(2022, 3, 1);
        assertTrue(task.matchesDate(dateToCheck));
    }

    @Test
    public void matchesDate_matchingDateAfterDeadline_returnsFalse() {
        LocalDateTime deadline = LocalDateTime.of(2022, 3, 1, 12, 0);
        DeadlineTask task = new DeadlineTask("Task1", deadline);
        LocalDate dateToCheck = LocalDate.of(2022, 3, 2);
        assertFalse(task.matchesDate(dateToCheck));
    }

    @Test
    public void matchesDate_nonMatchingDate_returnsFalse() {
        LocalDateTime deadline = LocalDateTime.of(2022, 3, 1, 12, 0);
        DeadlineTask task = new DeadlineTask("Task1", deadline);
        LocalDate dateToCheck = LocalDate.of(2022, 4, 1);
        assertFalse(task.matchesDate(dateToCheck));
    }
}
