package rick.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rick.RickUtils;

/**
 * Represents a test suite that tests the {@code TodoTask} class.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class TodoTaskTest {
    /**
     * Tests the {@code TodoTask::mark} method.
     */
    @Test
    public void mark() {
        RickTask todo = new TodoTask("task one");
        todo.setDone();
        assertEquals(
                "[T][X] task one",
                todo.toString()
        );
    }

    /**
     * Tests the {@code TodoTask::unMark} method.
     */
    @Test
    public void unMark() {
        RickTask todo = new TodoTask("task one");

        assertEquals(
                "[T][ ] task one",
                todo.toString()
        );
    }

    /**
     * Tests the {@code TodoTask::isOnDate} method when it should evaluate to false.
     * This never evaluates to true as a Todo has no date.
     */
    @Test
    public void isOnDate() {
        RickTask todo = new TodoTask("task one");
        assertEquals(false,
                todo.isOnDate(RickUtils.parseDate("2/2/23")));
    }


    /**
     * Tests the {@code TodoTask::toDbSchema} method.
     */
    @Test
    public void toDbSchema() {
        RickTask todo = new TodoTask("task one");
        assertEquals(
                "T|0|task one",
                todo.toDbSchema()
        );
    }
}
