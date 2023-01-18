public class Event extends Task {
    private String from;
    private String to;

    public Event(String content, String from, String to) {
        super(content);
        this.from = from;
        this.to = to;
    }

    public static Event create(String content) {
        /**
         * @param content what to place in this task.
         * @returns the output Event object.
         */
        String source = "Event Creation";

        String[] contentAndFrom = Parser.handleMissingField(content, "/from", "from", source);
        String[] fromAndTo = Parser.handleMissingField(content, "/to", "to", source);

        String parsedContent = contentAndFrom[0].strip();
        String from = fromAndTo[0].strip();
        String to = fromAndTo[1].strip();

        Parser.handleEmptyField(parsedContent, "content", source);
        Parser.handleEmptyField(from, "from", source);
        Parser.handleEmptyField(to, "to", source);

        return new Event(parsedContent, from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
