public class EventTask extends Task {
    private final String period;
    EventTask(String name, String period) {
        super(name);
        this.period = period.replace("/from", "from:").replace("/to", "to:");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + period + ")";
    }
}
