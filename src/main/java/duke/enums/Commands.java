package duke.enums;

import java.util.Arrays;

/**
 * Command strings
 */
public enum Commands {
    TODO("todo",
            "todo 'name'", "To add a task with only a name."
    ),
    DEADLINE("deadline",
            "deadline 'name' /by 'date'", "To add a task with a deadline."
    ),
    EVENT("event",
            "event 'name' /from 'date' /to 'date'", "To add an event with start and end time."
    ),
    BY("/by",
            "for deadline: /by 'date' in this format YYYY-MM-DDTHH:MM eg '2023-01-20T18:00'",
            "To specify a deadline for a task in this format YYYY-MM-DDTHH:MM eg '2023-01-20T18:00'"
    ),
    FROM("/from",
            "for event: /from 'date' in this format YYYY-MM-DDTHH:MM eg '2023-01-20T18:00'",
            "To specify the start date of an event in this format YYYY-MM-DDTHH:MM eg '2023-01-20T18:00'"
    ),
    TO("/to",
            "for event: /to 'date' in this format YYYY-MM-DDTHH:MM eg '2023-01-20T18:00'",
            "To specify the end date of an event in this format YYYY-MM-DDTHH:MM eg '2023-01-20T18:00'"
    ),
    MARK("mark",
            "mark 1", "To mark a task as done by specifying its number."
    ),
    UNMARK("unmark",
            "unmark 1", "To unmark a task as not done by specifying its number."
    ),
    DELETE("delete",
            "delete 1", "To remove a task by specifying its number."
    ),
    DEL("del",
            "del 1", "To remove a task by specifying its number."
    ),
    FIND("find",
            "find 'name'", "To find tasks with a specified name."
    ),
    SET("set",
            "set 'language'", "To set language for duke."
    ),
    LIST("list",
            "list", "List out the tasks in the task list"
    ),
    CLEAR("clear",
            "clear", "Clears the task list"
    ),
    EXPORT("export",
            "export", "To export tasks to a markdown file"
    ),
    BYE("bye",
            "bye", "To exit duke."
    ),
    EXIT("exit",
            "exit", "To exit duke."
    ),
    HELP("help",
            "show this message",
            "To show the available commands and explanations.");

    private final String cmd;
    private final String example;
    private final String explanation;

    /**
     * @param cmd given in the enum
     */
    Commands(String cmd, String example, String explanation) {
        this.cmd = cmd;
        this.example = example;
        this.explanation = explanation;
    }

    /**
     * Change the command to the user input thing
     *
     * @return String of the command
     */
    public String cmd() {
        return cmd;
    }

    /**
     * Length of command useful for parsing commands
     *
     * @return int Length of command
     */
    public int len() {
        return cmd.length();
    }

    /**
     * Get an example of how to use the command
     *
     * @return example for the command
     */
    @Override
    public String toString() {
        return this.cmd + ": " + this.example;
    }

    /**
     * Get how to use the command
     *
     * @return explanation for the command
     */
    public String getExplanation() {
        return this.cmd + ": " + this.explanation;
    }

    /**
     * Returns a formatted string on all the commands
     *
     * @param isGui
     * @return String with all the command inside
     */
    public static String helpMessages(boolean isGui) {
        String lineBreak = "\n      ";
        if (isGui) {
            lineBreak = "\n";
        }
        String[] helpStrings = Arrays.stream(Commands.values())
                .map(Enum::toString)
                .toArray(String[]::new);
        String returnString = Views.ALL_COMMAND_STRING.str() + lineBreak;
        for (String helpString : helpStrings) {
            returnString += helpString + lineBreak;
        }
        return returnString.trim();

    }
}
