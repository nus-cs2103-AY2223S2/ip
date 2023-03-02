package wessy.task;

public class FixedDurationTask extends Task {
    private final String requiredDuration;

    public FixedDurationTask(String description, String duration, boolean isDone){
        super(description, isDone);
        this.requiredDuration = duration;
    }

    public FixedDurationTask(String description, String duration) {
        this(description, duration, false);
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + " (for: " + requiredDuration + ")";
    }

    @Override
    public String saveAsStr(String separator) {
        return "F" + super.saveAsStr(separator) + separator + requiredDuration + "\n";
    }
}
