package task;

import java.time.LocalDateTime;

import parser.DateParser;
import parser.Parser;

/**
 * Event task with a 'from' and a 'to' clause.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor for Event.
     * @param content Content to be placed inside the Event.
     * @param from Start datetime of the Event.
     * @param to End datetime of the Event.
     */
    public Event(String content, String from, String to) {
        super(content);
        this.from = DateParser.parse(from);
        this.to = DateParser.parse(to);
    }

    /**
     * Creates a new Event object.
     * @param content The content of the event.
     * @return A new Event object.
     */
    public static Event create(String content) {
        String source = "Task.Event Creation";

        String[] contentAndFrom = Parser.handleMissingField(content, "/from", "from", source);
        String[] fromAndTo = Parser.handleMissingField(contentAndFrom[1], "/to", "to", source);

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
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                DateParser.formatDateToPrint(this.from),
                DateParser.formatDateToPrint(this.to));
    }

    @Override
    public String toStorageString() {
        return String.format(
                "E|%d|%s /from %s /to %s",
                this.isMarked() ? 1 : 0,
                this.getContent(),
                DateParser.formatDateToStore(this.from),
                DateParser.formatDateToStore(this.to));
    }
}
