package wessy.task;

public class DoAfterTask extends Task {
    private final String after;

    public DoAfterTask(String description, String after, boolean isDone){
        super(description, isDone);
        this.after = after;
    }

    public DoAfterTask(String description, String after) {
        this(description, after, false);
    }

    @Override
    public String toString() {
        return "[A]" + super.toString() + " (after: " + after + ")";
    }

    @Override
    public String saveAsStr(String separator) {
        return "A" + super.saveAsStr(separator) + separator + after + "\n";
    }
}
