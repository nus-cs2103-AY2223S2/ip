package duke;

import duke.exception.InvalidFormatException;
import duke.exception.UnrecognisedCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.time.LocalDate;

public class Parser {
    protected final DateTimeFormatter inputFormat;
    protected final DateTimeFormatter outputFormat;

    /**
     * Creates a parser that can decipher many Strings
     *
     * @param inputFormat A DateTimeFormatter to process user inputs
     * @param outputFormat A DateTimeFormatter to format date outputs to users
     */
    public Parser(String inputFormat, String outputFormat) {
        this.inputFormat = DateTimeFormatter.ofPattern(inputFormat);
        this.outputFormat = DateTimeFormatter.ofPattern(outputFormat);
    }

    public LocalDate parseDate(String text) {
        LocalDate a = LocalDate.parse(text, inputFormat);   // read input as LocalDate using input format
        String b = a.format(outputFormat);                  // Convert LocalDate to String to change the format
        return LocalDate.parse(b, outputFormat);            // Recreate LocalDate with output format
    }

    public String dateToLogFormat(LocalDate date) {
        return date.format(inputFormat);
    }
}
