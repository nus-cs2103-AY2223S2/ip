package task;

import jarvis.exception.CommandParseException;
import jarvis.exception.InvalidParameterException;
import jarvis.task.DeadlineTask;
import jarvis.task.TaskFilter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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
        TaskFilter earlyFilter = new TaskFilter(null, DEADLINE.minusDays(1).toString());
        TaskFilter lateFilter = new TaskFilter(DEADLINE.plusDays(1).toString(), null);
        TaskFilter validFilter = new TaskFilter(
                DEADLINE.minusDays(1).toString(),
                DEADLINE.plusDays(1).toString()
        );
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
