package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Parser {
    protected final DateTimeFormatter inputFormat;
    protected final DateTimeFormatter outputFormat;

    /**
     * Constructs a parser that can decipher Strings for Duke
     *
     * @param inputFormat a DateTimeFormatter pattern to process inputs
     * @param outputFormat a DateTimeFormatter pattern to format date outputs to users
     */
    public Parser(String inputFormat, String outputFormat) {
        this.inputFormat = DateTimeFormatter.ofPattern(inputFormat);
        this.outputFormat = DateTimeFormatter.ofPattern(outputFormat);
    }

    /**
     * Parses a string into a date
     *
     * @param text text to parse
     * @return LocalDate in the output format
     */
    public LocalDate parseDate(String text) {
        return LocalDate.parse(text, inputFormat);
    }

    /**
     * Converts a date into a string of the input format
     *
     * @param date to be converted
     * @return string in input format
     */
    public String dateToOutputFormat(LocalDate date) {
        return date.format(outputFormat);
    }
}
