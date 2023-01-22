package aqua.aquatask;

import java.time.LocalDateTime;
import java.util.Optional;

import aqua.util.DateUtils;

public class AquaEvent extends AquaTask {
    public static final String FROM_TAG = "from";
    public static final String TO_TAG = "to";

    private final boolean isComplete;
    private final LocalDateTime from;
    private final LocalDateTime to;


    public AquaEvent(String name, LocalDateTime from, LocalDateTime to) {
        this(name, false, from, to);
    }

    public AquaEvent(String name, boolean isComplete, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.isComplete = isComplete;
        this.from = from;
        this.to = to;
    }


    @Override
    public AquaEvent mark(boolean isComplete) {
        return new AquaEvent(this.getName(), isComplete, this.from, this.to);
    }


    @Override
    public boolean isComplete() {
        return isComplete;
    }


    @Override
    public Optional<LocalDateTime> getStart() {
        return Optional.ofNullable(from);
    }


    @Override
    public Optional<LocalDateTime> getEnd() {
        return Optional.ofNullable(to);
    }

    
    @Override
    public String getReloadString() {
        return String.format(
            "event %s /%s %s /%s %s /%s %s",
            getName(),
            FROM_TAG, from,
            TO_TAG, to,
            IS_COMPLETED_TAG, isComplete
        );
    }


    @Override
    public String toString() {
        return String.format(
            "[E]%s (from: %s to: %s)",
            super.toString(),
            DateUtils.formatNice(from),
            DateUtils.formatNice(to)
        );
    }
}
