import java.time.format.DateTimeFormatter;

/**
 *
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


    final DateTimeFormatter formatter;
    DateTimeFormat(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    DateTimeFormat(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

}
