import java.util.Arrays;

public class Parser {
    private String[] parseTodo(String command) throws ZeroLengthDescriptionException {
        String[] split_command = command.split(" ");
        checkCommandLength(split_command);
        String[] taskNameSplit = Arrays.copyOfRange(split_command, 1, split_command.length);
        String taskName = String.join(" ", taskNameSplit);
        String[] parsedCommand = new String[] {split_command[0], taskName};
        return parsedCommand;
    }

    private String[] parseDeadline(String command) throws ZeroLengthDescriptionException {
        String[] split_command = command.split("/");
        String by = split_command[1];
        //need to split task type and name
        String[] taskAndName = split_command[0].split(" ");
        checkCommandLength(taskAndName);
        String[] taskNameSplit = Arrays.copyOfRange(taskAndName, 1, taskAndName.length);
        String taskName = String.join(" ", taskNameSplit);
        String[] parsedCommand = new String[] {taskAndName[0], taskName, by};
        return parsedCommand;
    }

    private String[] parseEvent(String command) throws ZeroLengthDescriptionException {
        String[] split_command = command.split("/");
        String from = split_command[1];
        String to = split_command[2];
        //need to split task type and name
        String[] taskAndName = split_command[0].split(" ");
        checkCommandLength(taskAndName);
        String[] taskNameSplit = Arrays.copyOfRange(taskAndName, 1, taskAndName.length);
        String taskName = String.join(" ", taskNameSplit);
        String[] parsedCommand = new String[] {taskAndName[0], taskName, from, to};
        return parsedCommand;
    }

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
