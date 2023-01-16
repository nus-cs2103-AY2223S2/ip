public class DeadlineTask extends Task {

    private static final String EVENT_SYMBOL = "D";
    private String endTime;

    public DeadlineTask(String description, String endTime) {
        super(description, EVENT_SYMBOL);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + endTime + ")";
    }
}