package jarvis.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jarvis.duration.Duration;
import jarvis.exception.command.MissingParameterException;

public class TimedTaskTest {
    private static final String BODY = "a timed task test";
    private static final int DAYS = 1;
    private static final int HOURS = 2;
    private static final int MINUTES = 3;

    private final Duration duration;
    private TimedTask task;

    public TimedTaskTest() {
        duration = new Duration().setDays(DAYS).setHours(HOURS).setMinutes(MINUTES);
        try {
            task = new TimedTask(BODY, duration);
        } catch (MissingParameterException e) {
            e.printStackTrace();
            task = null;
        }
    }

    @Test
    public void serializeTest() {
        assert task != null;
        Assertions.assertEquals(
                String.format("%s / %b / %s / %s %s %s", TimedTask.ID, task.isDone(), BODY, DAYS, HOURS, MINUTES),
                task.serialize()
        );
    }

    @Test
    public void toStringTest() {
        assert task != null;
        String doneFlag = task.isDone() ? "X" : " ";
        Assertions.assertEquals(
                String.format("[%s][%s] %s (duration: %s)", TimedTask.ID, doneFlag, BODY, duration.toString()),
                task.toString()
        );
    }
}
