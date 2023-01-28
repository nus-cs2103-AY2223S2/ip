package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public String getCmd(String input) {
        return input.split(" ")[0];
    }

    public String getDescription(String input) {
        return input.split(" ")[1];
    }

    public String getPreDescription(String description, String split) {
        return description.split(split)[0];
    }

    public String getPostDescription(String description, String split) {
        return description.split(split)[1];
    }

    public LocalDate getDateTime(String description) {
        return LocalDate.parse(description);
    }

    public LocalDate getDateTime(String description, DateTimeFormatter formatter) {
        return LocalDate.parse(description, formatter);
    }
}
