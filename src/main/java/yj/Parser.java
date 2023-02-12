package yj;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    static public Integer parseDeleteCommand(String input) {
        Integer taskNumber = Integer.parseInt(input.split(" ")[1]);
        return taskNumber;
    }

    /**
     * Parses the mark command.
     * @param input The input string.
     * @return The task number.
     */
    static public Integer parseMarkCommand(String input) {
        Integer taskNumber = Integer.parseInt(input.split(" ")[1]);
        return taskNumber;
    }

    /**
     * Parses the unmark command.
     * @param input The input string.
     * @return The task number.
     */
    static public Integer parseUnMarkCommand(String input) {
        Integer taskNumber = Integer.parseInt(input.split(" ")[1]);
        return taskNumber;
    }

    /**
     * Parses the todo command.
     * @param input The input string.
     * @return The todo description.
     */
    static public String parseToDoCommand(String input) {
        String description = input.replaceAll("todo ", "").trim();
        return description;
    }

    /**
     * Parses the deadline command.
     * @param input The input string.
     * @return A map containing the description and the deadline.
     */
    static public Map<String, String> parseDeadlineCommand(String input) {
        String by = input.split("/by")[1].trim();
        String description = input.split("/by")[0].trim();
        Map<String, String> result = new HashMap<String, String>();
        result.put("description", description);
        result.put("by", by);
        return result;
    }

    /**
     * Parses the event command.
     * @param input The input string.
     * @return A map containing the description, the start time and the end time.
     */
    static public Map<String, String> parseEventCommand(String input) {
        String from = input.split("/from")[1].split("/to")[0].trim();
        String to = input.split("/to")[1].trim();
        String description = input.split("/from")[0].trim();

        Map<String, String> result = new HashMap<String, String>();
        result.put("description", description);
        result.put("from", from);
        result.put("to", to);
        return result;
    }


    /**
     * Parses the command.
     * @param command The command string.
     * @return The command enum.
     */
    static public Command parseCommand(String command) {
        if (command.equals("bye")) {
            return Command.BYE;
        } else if (command.equals("list")) {
            return Command.LIST;
        } else if (command.startsWith("delete")) {
            return Command.DELETE;
        } else if (command.startsWith("mark")) {
            return Command.MARK;
        } else if (command.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (command.startsWith("todo")) {
            return Command.TODO;
        } else if (command.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (command.startsWith("event")) {
            return Command.EVENT;
        } else {
            return null;
        }
    }

}
