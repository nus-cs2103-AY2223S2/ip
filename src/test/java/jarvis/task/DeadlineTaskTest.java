package jarvis.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import jarvis.exception.command.CommandParseException;
import jarvis.exception.command.InvalidParameterException;


public class DeadlineTaskTest {
    private static final String BODY = "a deadline test";
    private static final LocalDate DEADLINE = LocalDate.now();

    private DeadlineTask task;

    public DeadlineTaskTest() {
        try {
            this.task = new DeadlineTask(BODY, DEADLINE.toString());
        } catch (CommandParseException e) {
            this.task = null;
        }
    }

    @Test
    public void filterTest() throws InvalidParameterException {
        assert task != null;
        TaskFilter earlyFilter = new TaskFilter().setBeforeDate(DEADLINE.minusDays(1).toString());
        TaskFilter lateFilter = new TaskFilter().setAfterDate(DEADLINE.plusDays(1).toString());
        TaskFilter validFilter = new TaskFilter()
                .setAfterDate(DEADLINE.minusDays(1).toString())
                .setBeforeDate(DEADLINE.plusDays(1).toString());
        assertFalse(task.satisfies(earlyFilter));
        assertFalse(task.satisfies(lateFilter));
        assertTrue(task.satisfies(validFilter));
    }

    @Test
    public void serializeTest() {
        assert task != null;
        assertEquals(
                String.format("D / %b / %s / %s", task.isDone(), BODY, DEADLINE),
                task.serialize()
        );
    }

    @Test
    public void toStringTest() {
        assert task != null;
        assertEquals(
                String.format("[D][%s] %s (by: %s)", task.isDone() ? "X" : " ", BODY, DEADLINE),
                task.toString()
        );
    }
}
