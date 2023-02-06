package duke;

import java.time.format.DateTimeFormatter;

/**
 * Represents all the DateTimeFormats that is supported by this chatbot
 */
public enum DateTimeFormat {
    hyphenated(DateTimeFormatter.ISO_LOCAL_DATE),
    hyphenated2("yyyy-m-d HHmm"),
    hyphenated3("yyyy-m-d HHmma"),
    nonDelimited(DateTimeFormatter.BASIC_ISO_DATE),
    slashed("d/M/yyyy HHmm"),
    slashed2("d/M/yyyy HHmma"),
    slashed3("d/M/yyyy"),
    defaultOutput("MMM dd yyyy HHmma");


    public final DateTimeFormatter formatter;

    /**
     * Constructor for storing information in the enum
     * @param pattern string representing the pattern
     */
    DateTimeFormat(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * Constructor for storing information in the enum
     * @param formatter format that should be stored in instance attribute formatter
     */
    DateTimeFormat(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

}
