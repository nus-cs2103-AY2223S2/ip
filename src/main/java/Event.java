public class Event extends Task{
    private final String fromStr;
    private final String toStr;
    public Task.Type type;

    public Event(String task, String from, String to) {
        super(task);
        fromStr = from;
        toStr = to;
        type = Type.EVENT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From " + fromStr + " to " + toStr + ")";
    }

    @Override
    public String toCommand() {
        return "event " + super.taskStr + " /from " + fromStr + " /to " + toStr + (super.done ? "\nmark last": "");
    }
}
