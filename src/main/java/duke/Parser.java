package duke;

import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/** Parser to parse the commands by the user */
public class Parser {
    private String[] parseTodo(String command) throws ZeroLengthDescriptionException {
        String[] split_command = command.split(" ");
        checkCommandLength(split_command);
        String[] taskNameSplit = Arrays.copyOfRange(split_command, 1, split_command.length);
        String taskName = String.join(" ", taskNameSplit);
        String[] parsedCommand = new String[] {split_command[0], taskName};
        return parsedCommand;
    }

    private String reformatDate(String unformattedDate) {
        LocalDate deadlineDateObj;
        DateTimeFormatter dtf;
        String formattedDeadline;

        deadlineDateObj = LocalDate.parse(unformattedDate);
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formattedDeadline = deadlineDateObj.format(dtf);
        return formattedDeadline;
    }

    private String[] parseDeadline(String command) throws ZeroLengthDescriptionException {
        String[] split_command = command.split("/");
        String by = split_command[1];
        //need to split task type and name
        String[] taskAndName = split_command[0].split(" ");
        checkCommandLength(taskAndName);
        String[] taskNameSplit = Arrays.copyOfRange(taskAndName, 1, taskAndName.length);
        String taskName = String.join(" ", taskNameSplit);
        String unformattedDeadline = by.split(" ")[1];
        String formattedDeadline = reformatDate(unformattedDeadline);
        String[] parsedCommand = new String[] {taskAndName[0], taskName, formattedDeadline};
        return parsedCommand;
    }

    private String[] parseEvent(String command) throws ZeroLengthDescriptionException {
        String[] split_command = command.split("/");
        String from = split_command[1];
        String to = split_command[2];
        //format from and to
        String unformattedFrom = from.split(" ")[1];
        String unformattedTo = to.split(" ")[1];
        String formattedFrom = reformatDate(unformattedFrom);
        String formattedTo = reformatDate(unformattedTo);
        //need to split task type and name
        String[] taskAndName = split_command[0].split(" ");
        checkCommandLength(taskAndName);
        String[] taskNameSplit = Arrays.copyOfRange(taskAndName, 1, taskAndName.length);
        String taskName = String.join(" ", taskNameSplit);
        String[] parsedCommand = new String[] {taskAndName[0], taskName, formattedFrom, formattedTo};
        return parsedCommand;
    }

    /**
     * Parses the command input by the user, and returns the key entities
     * in the command.
     * @param command The command input by the user.
     * @return A String array containing the extracted key entities of the command.
     * @throws ZeroLengthDescriptionException Thrown with an empty task description is given.
     */
    public String[] parseCommand(String command) throws ZeroLengthDescriptionException {
        String[] splitCommand = command.split(" ");
        String[] parsedCommand;
        switch (splitCommand[0]) {
            case "todo":
                parsedCommand = parseTodo(command);
                break;
            case "deadline":
                parsedCommand = parseDeadline(command);
                break;
            case "event":
                parsedCommand = parseEvent(command);
                break;
            default:
                return splitCommand;
        }
        return parsedCommand;
    }

    private void checkCommandLength(String[] strArray) throws ZeroLengthDescriptionException {
        if (strArray.length <= 1) {
            throw new ZeroLengthDescriptionException();
        }
    }
}
