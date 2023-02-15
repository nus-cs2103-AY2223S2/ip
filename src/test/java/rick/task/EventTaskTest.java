package rick.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rick.RickUtils;

/**
 * Represents a test suite that tests the {@code EventTask} class.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class EventTaskTest {
    /**
     * Tests the {@code EventTask::mark} method.
     */
    @Test
    public void mark() {
        RickTask event = new EventTask(
                "task one",
                RickUtils.parseDateTime("2/2/23 0001"),
                RickUtils.parseDateTime("2/2/23 0005")
        );
        event.setDone();
        assertEquals(
                "[E][X] task one (from: Feb 2 2023 12:01AM to: Feb 2 2023 12:05AM)",
                event.toString()
        );
    }

    /**
     * Tests the {@code EventTask::unMark} method.
     */
    @Test
    public void unMark() {
        RickTask event = new EventTask(
                "task one",
                RickUtils.parseDateTime("2/2/23 0001"),
                RickUtils.parseDateTime("2/2/23 0005")
        );

        assertEquals(
                "[E][ ] task one (from: Feb 2 2023 12:01AM to: Feb 2 2023 12:05AM)",
                event.toString()
        );
    }

    /**
     * Tests the {@code EventTask::isOnDate} method when it should evaluate to true.
     */
    @Test
    public void isOnDate() {
        RickTask event = new EventTask(
                "task one",
                RickUtils.parseDateTime("2/2/23 0001"),
                RickUtils.parseDateTime("2/2/23 0005")
        );
        assertEquals(true,
                event.isOnDate(RickUtils.parseDate("2/2/23")));
    }

    /**
     * Tests the {@code EventTask::isOnDate} method when it should evaluate to false.
     */
    @Test
    public void isNotOnDate() {
        RickTask event = new EventTask(
                "task one",
                RickUtils.parseDateTime("2/2/23 0001"),
                RickUtils.parseDateTime("2/2/23 0005")
        );
        assertEquals(false,
                event.isOnDate(RickUtils.parseDate("1/2/23")));
    }

    /**
     * Tests the {@code EventTask::toDbSchema} method.
     */
    @Test
    public void toDbSchema() {
        RickTask event = new EventTask(
                "task one",
                RickUtils.parseDateTime("2/2/23 0001"),
                RickUtils.parseDateTime("2/2/23 0005")
        );
        assertEquals(
                "E|0|task one|2/2/23 0001|2/2/23 0005",
                event.toDbSchema()
        );
    }
}
