public class EventTask extends Task {
    private final String period;
    EventTask(String name, String period) throws DukeException {
        this(argumentCheck(period), name, period);
    }
    private EventTask(Void ignored, String name, String period) throws DukeException {
        super(name);
        this.period = period.replace("/from", "from:").replace("/to", "to:");
    }

    private static Void argumentCheck(String period) throws DukeException {
        if (period.isEmpty()) {
            throw new DukeException("Empty period for event task");
        }
        return null;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + period + ")";
    }
}
