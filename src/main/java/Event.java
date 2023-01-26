public class Event extends Task {
    protected String from;
    protected String to;

    Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveFormat() {
        return "E||" + super.toSaveFormat() + "||" + this.from + "||" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + ", to: " + this.to + ")";
    }

    public static Event fromSaveFormat(String savedData) {
        String[] inputs = savedData.split("\\|\\|");
        Event generatedEvent = new Event(inputs[2], inputs[3], inputs[4]);
        if (inputs[1].equals("1")) {
            generatedEvent.setCompleted(true);
        }
        return generatedEvent;
    }
}
