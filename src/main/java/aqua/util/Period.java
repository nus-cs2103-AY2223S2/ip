package aqua.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


/** Represents a time range. */
public abstract class Period {
    public abstract LocalDateTime getStart();
    public abstract LocalDateTime getEnd();


    public long duration() {
        return getStart().until(getEnd(), ChronoUnit.MICROS);
    }
}
