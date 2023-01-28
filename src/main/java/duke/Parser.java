package duke;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Parses user input into usable terms for Duke.
 */
public class Parser {
    /**
     * Returns command from user input.
     *
     * @param input User input.
     * @return Command as String.
     */
    public String getCmd(String input) {
        return input.split(" ")[0];
    }

    /**
     * Returns description of task from user input.
     *
     * @param input User input.
     * @return Description of task as String.
     */
    public String getDescription(String input) {
        return input.split(" ")[1];
    }

    /**
     * Returns the front part of description of task.
     *
     * @param description Description of task.
     * @param split String to split the description.
     * @return Front part of the description as String.
     */
    public String preDescription(String description, String split) {
        return description.split(split)[0];
    }

    /**
     * Returns the back part of description of task.
     *
     * @param description Description of task.
     * @param split String to split the description.
     * @return Back part of the description as String.
     */
    public String postDescription(String description, String split) {
        return description.split(split)[1];
    }

    /**
     * Returns the date time format of given description of task.
     *
     * @param description Description of task.
     * @return Date/time format of given description as LocalDate.
     */
    public LocalDate dateTime(String description) {
        return LocalDate.parse(description);
    }
}
