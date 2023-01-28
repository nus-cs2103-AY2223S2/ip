public class Event extends Task{
    private final String fromStr;
    private final String toStr;

    public Event(String task, String from, String to) {
        super(task);
        fromStr = from;
        toStr = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From " + fromStr + " to " + toStr + ")";
    }

    @Override
    public String toCommand() {
        return "event " + super.description + " /from " + fromStr + " /to " + toStr + (super.isDone ? "\nmark last": "");
    }
}
