package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Duration;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class FixedDurationTaskTest {

    @Test
    void getDuration_validDuration_correctDuration() {
        Duration duration = Duration.ofHours(5);
        FixedDurationTask task = new FixedDurationTask("Test task", duration);
        assertEquals(duration, task.getDuration());
    }

    @Test
    void storageString_validTask_correctStorageString() {
        Duration duration = Duration.ofHours(5);
        FixedDurationTask task = new FixedDurationTask("Test task", duration);
        String expected = "[F] | [ ] | Test task | PT5H";
        assertEquals(expected, task.storageString());
    }

    @Test
    void storageString_completedTask_correctStorageString() {
        Duration duration = Duration.ofHours(5);
        FixedDurationTask task = new FixedDurationTask("Test task", duration);
        task.markAsDone();
        String expected = "[F] | [X] | Test task | PT5H";
        assertEquals(expected, task.storageString());
    }

    @Test
    void matchesDate_validDate_false() {
        Duration duration = Duration.ofHours(5);
        FixedDurationTask task = new FixedDurationTask("Test task", duration);
        LocalDate date = LocalDate.now();
        assertFalse(task.matchesDate(date));
    }

    @Test
    void toString_validTask_correctStringRepresentation() {
        Duration duration = Duration.ofHours(5);
        FixedDurationTask task = new FixedDurationTask("Test task", duration);
        String expected = "[F][ ] Test task ( duration: 5h )";
        assertEquals(expected, task.toString());
    }

    @Test
    void equals_sameTask_true() {
        Duration duration = Duration.ofHours(5);
        FixedDurationTask task1 = new FixedDurationTask("Test task", duration);
        FixedDurationTask task2 = new FixedDurationTask("Test task", duration);
        assertEquals(task1, task2);
    }

    @Test
    void equals_differentInformation_false() {
        Duration duration = Duration.ofHours(5);
        FixedDurationTask task1 = new FixedDurationTask("Test task", duration);
        FixedDurationTask task2 = new FixedDurationTask("Different task", duration);
        assertNotEquals(task1, task2);
    }

    @Test
    void equals_differentDuration_false() {
        Duration duration1 = Duration.ofHours(5);
        Duration duration2 = Duration.ofHours(10);
        FixedDurationTask task1 = new FixedDurationTask("Test task", duration1);
        FixedDurationTask task2 = new FixedDurationTask("Test task", duration2);
        assertNotEquals(task1, task2);
    }

    @Test
    void equals_differentStatus_false() {
        Duration duration = Duration.ofHours(5);
        FixedDurationTask task1 = new FixedDurationTask("Test task", duration);
        FixedDurationTask task2 = new FixedDurationTask("Test task", duration);
        task1.markAsDone();
        assertNotEquals(task1, task2);
    }

    @Test
    void hashCode_differentInformation_differentHashCode() {
        Duration duration = Duration.ofHours(5);
        FixedDurationTask task1 = new FixedDurationTask("Test task", duration);
        FixedDurationTask task2 = new FixedDurationTask("Different task", duration);
        assertNotEquals(task1.hashCode(), task2.hashCode());
    }
}
