package task;

import parser.DateParser;
import parser.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String content, String from, String to) {
        super(content);
        this.from = DateParser.parse(from);
        this.to = DateParser.parse(to);
    }

    public static Event create(String content) {
        /**
         * @param content what to place in this seedu.task.
         * @returns the output Task.Event object.
         */
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
