public class DeadlineTask extends Task {

    private static final String EVENT_SYMBOL = "D";

    public DeadlineTask(String description) {
        super(description, EVENT_SYMBOL);
    }
}