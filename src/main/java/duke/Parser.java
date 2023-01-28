package duke;

import java.time.LocalDate;

public class Parser {

    public String getCmd(String input) {
        return input.split(" ")[0];
    }

    public String getDescription(String input) {
        return input.split(" ")[1];
    }

    public String preDescription(String description, String split) {
        return description.split(split)[0];
    }

    public String postDescription(String description, String split) {
        return description.split(split)[1];
    }

    public LocalDate dateTime(String description) {
        return LocalDate.parse(description);
    }
}
