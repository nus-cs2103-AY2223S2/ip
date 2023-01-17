public class Event extends Task {
    public String from;
    public String to;

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
        String[] contentAndFrom = content.split("/from");
        if (contentAndFrom.length <= 1) {
            throw new InputFormatException("Event Creation", "Haiya where your /from?", null);
        }
        String[] fromAndTo = contentAndFrom[1].split("/to");
        if (fromAndTo.length <= 1) {
            throw new InputFormatException("Event Creation", "Haiya where your /to?", null);
        }
        String parsedContent = contentAndFrom[0].strip();
        String from = fromAndTo[0].strip();
        String to = fromAndTo[1].strip();
        if (parsedContent.equals("")) {
            throw new InputFormatException("Event Creation", "Haiya content empty.", null);
        }
        if (from.equals("")) {
            throw new InputFormatException("Event Creation", "Haiya from empty.", null);
        }
        if (to.equals("")) {
            throw new InputFormatException("Event Creation", "Haiya to empty.", null);
        }
        return new Event(parsedContent, from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
