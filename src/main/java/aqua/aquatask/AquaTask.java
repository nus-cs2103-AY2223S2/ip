package aqua.aquatask;

import java.time.LocalDateTime;
import java.util.Optional;

public abstract class AquaTask {
    private final String name;


    public AquaTask(String name) {
        this.name = name;
    }

    public AquaTask(AquaTask task) {
        this.name = task.name;
    }


    public abstract AquaTask mark(boolean isComplete);
    
    public abstract boolean isComplete();


    public String getName() {
        return name;
    }


    public Optional<LocalDateTime> getStart() {
        return Optional.empty();
    }


    public Optional<LocalDateTime> getEnd() {
        return Optional.empty();
    }


    @Override
    public String toString() {
        return String.format("%s %s",
            getStatusString(),
            getName()
        );
    }

    private String getStatusString() {
        return isComplete() ? "[X]" : "[ ]";
    }
}
