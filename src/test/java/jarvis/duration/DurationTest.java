package jarvis.duration;

import org.junit.jupiter.api.Test;

public class DurationTest {
    private static final int DAYS = 1;
    private static final int HOURS = 2;
    private static final int MINUTES = 3;
    private static final String EMPTY_SERIAL = "0 0 0";
    private static final String EMPTY_STRING = "None";
    private static final String FULL_SERIAL = String.format("%d %d %d", DAYS, HOURS, MINUTES);
    private static final String FULL_STRING = String.format("%d days, %d hours, %d minutes", DAYS, HOURS, MINUTES);

    private final Duration emptyDuration;
    private final Duration fullDuration;

    public DurationTest() {
        emptyDuration = new Duration();
        fullDuration = new Duration()
                .setDays(DAYS)
                .setHours(HOURS)
                .setMinutes(MINUTES);
    }

    @Test
    public void isEmptyTest() {
        assert emptyDuration.isEmpty();
        assert !fullDuration.isEmpty();
    }

    @Test
    public void serializeTest() {
        assert EMPTY_SERIAL.equals(emptyDuration.serialize());
        assert FULL_SERIAL.equals(fullDuration.serialize());
    }

    @Test
    public void toStringTest() {
        assert EMPTY_STRING.equals(emptyDuration.toString());
        assert FULL_STRING.equals(fullDuration.toString());
    }

    @Test
    public void deserializeTest() {
        assert EMPTY_STRING.equals(Duration.deserialize(EMPTY_SERIAL).toString());
        assert FULL_STRING.equals(Duration.deserialize(FULL_SERIAL).toString());
    }
}
