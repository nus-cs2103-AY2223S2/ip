package aqua.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Timeable {
    public abstract LocalDateTime getStart();
    public abstract LocalDateTime getEnd();


    public long duration() {
        return getStart().until(getEnd(), ChronoUnit.MICROS);
    }
}
