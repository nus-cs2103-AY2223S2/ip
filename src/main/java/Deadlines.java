public class Deadlines extends Item {
    private static final String TYPE = "[D]";
    private String deadline;

    public Deadlines(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String messageWhenAdded() {
        return "DukeyList just added a new deadline:";
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X] " + this.getName() + " (" + this.deadline + ")";
        }
        return TYPE + "[ ] " + this.getName() + " (" + this.deadline + ")";
    }
}
