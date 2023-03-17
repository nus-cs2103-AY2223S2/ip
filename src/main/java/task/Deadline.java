package task;

import java.time.LocalDateTime;

import parser.DateParser;
import parser.Parser;

/**
 * Deadline object with a 'by' clause.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructor for the Deadline object.
     * @param content Content to be put in the object.
     * @param by The deadline of the task as a String.
     */
    public Deadline(String content, String by) {
        super(content);
        this.by = DateParser.parse(by);
    }

    /**
     * Creates a new Deadline object.
     * @param content The content of the object.
     * @return A new deadline object.
     */
    public static Deadline create(String content) {
        String source = "Task.Deadline Creation";
        String[] contentAndBy = Parser.handleMissingField(content, "/by", "by", source);

        String parsedContent = contentAndBy[0].strip();
        String by = contentAndBy[1].strip();

        Parser.handleEmptyField(parsedContent, "content", source);
        Parser.handleEmptyField(by, "by", source);

        return new Deadline(parsedContent, by);
    }

    @Override
    public boolean isSoon() {
        return this.by.isBefore(LocalDateTime.now().plusDays(7)) && this.by.isAfter(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                DateParser.formatDateToPrint(this.by));
    }

    @Override
    public String toStorageString() {
        return String.format(
                "D|%d|%s /by %s",
                this.isMarked() ? 1 : 0,
                this.getContent(),
                DateParser.formatDateToStore(this.by));
    }
}
