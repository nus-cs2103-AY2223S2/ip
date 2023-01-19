package task;

import jarvis.exception.CommandParseException;
import jarvis.task.EventTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {
    private static final String BODY = "an event test";
    private static final LocalDate FROM_DATE = LocalDate.now();
    private static final LocalDate TO_DATE = LocalDate.now().plusMonths(1);

    private EventTask task;

    public EventTaskTest() {
        try {
            this.task = new EventTask(BODY, FROM_DATE.toString(), TO_DATE.toString());
        } catch (CommandParseException e) {
            this.task = null;
        }
    }

    @Test
    public void serializeTest() {
        assert task != null;
        assertEquals(
                String.format("E / %b / %s / %s / %s", task.isDone(), BODY, FROM_DATE, TO_DATE),
                task.serialize()
        );
    }

    @Test
    public void toStringTest() {
        assert task != null;
        assertEquals(
                String.format("[E][%s] %s (from: %s, to: %s)", task.isDone() ? "X" : " ", BODY, FROM_DATE, TO_DATE),
                task.toString()
        );
    }
}
