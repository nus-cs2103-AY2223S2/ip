public class Event extends Task {
    public String from;
    public String to;

    public Event(String content, String from, String to) {
        super(content);
        this.from = from;
        this.to = to;
    }

    public static Event create(String content) {
        String[] splitted = content.split("/from|/to");
        if (splitted.length <= 2) {
            System.out.println("Invalid input for event creation.");
            return null;
        }
        return new Event(splitted[0].strip(), splitted[1].strip(), splitted[2].strip());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
