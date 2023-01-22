package aqua.aquatask;

import java.time.LocalDateTime;
import java.util.Optional;

import aqua.util.DateUtils;

public class AquaDeadline extends AquaTask {
    public static final String BY_TAG = "by";

    private final boolean isComplete;
    private final LocalDateTime by;


    public AquaDeadline(String name, LocalDateTime by) {
        this(name, false, by);
    }

    public AquaDeadline(String name, boolean isComplete, LocalDateTime by) {
        super(name);
        this.isComplete = isComplete;
        this.by = by;
    }


    @Override
    public AquaDeadline mark(boolean isComplete) {
        return new AquaDeadline(this.getName(), isComplete, this.by);
    }


    @Override
    public boolean isComplete() {
        return this.isComplete;
    }


    @Override
    public Optional<LocalDateTime> getEnd() {
        return Optional.ofNullable(by);
    }
    
    
    public String getReloadString() {
        return String.format(
            "deadline %s /%s %s /%s %s",
            getName(),
            BY_TAG, by,
            IS_COMPLETED_TAG, isComplete
        );
    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DateUtils.formatNice(by));
    }
}
