package rick.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rick.RickUtils;

/**
 * Represents a test suite that tests the {@code DeadlineTask} class.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class DeadlineTaskTest {
    /**
     * Tests the {@code DeadlineTask::mark} method.
     */
    @Test
    public void mark() {
        RickTask deadline = new DeadlineTask("task one",
                RickUtils.parseDateTime("2/2/23 0000"));
        deadline.markUndone();
        assertEquals("[D][ ] task one (by: Feb 02 2023 12:00AM)", deadline.toString());
    }

    /**
     * Tests the {@code DeadlineTask::unMark} method.
     */
    @Test
    public void unMark() {
        RickTask deadline = new DeadlineTask("task one",
                RickUtils.parseDateTime("2/2/23 0000"));
        deadline.setDone();
        assertEquals("[D][X] task one (by: Feb 02 2023 12:00AM)", deadline.toString());
    }

    /**
     * Tests the {@code DeadlineTask::isOnDate} method when it should evaluate to true.
     */
    @Test
    public void isOnDate() {
        RickTask deadline = new DeadlineTask("task one",
                RickUtils.parseDateTime("2/2/23 0000"));
        assertEquals(true,
                deadline.isOnDate(RickUtils.parseDate("2/2/23")));
    }

    /**
     * Tests the {@code DeadlineTask::isOnDate} method when it should evaluate to false.
     */
    @Test
    public void isNotOnDate() {
        RickTask deadline = new DeadlineTask("task one",
                RickUtils.parseDateTime("2/2/23 0000"));
        assertEquals(false,
                deadline.isOnDate(RickUtils.parseDate("1/2/23")));
    }

    /**
     * Tests the {@code DeadlineTask::toDbSchema} method.
     */
    @Test
    public void toDbSchema() {
        RickTask deadline = new DeadlineTask("task one",
                RickUtils.parseDateTime("2/2/23 0000"));
        assertEquals(
                "D|0|task one|2/2/23 0000",
                deadline.toDbSchema()
        );
    }
}
