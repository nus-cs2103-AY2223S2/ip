public class Parser {
    // Ensure valid task number is provided
    private static int validateTaskNum(String input) throws EmptyTasknumException, NumberFormatException {
        String[] taskNumSplit = input.split(" ");
        if (taskNumSplit.length <= 1 || taskNumSplit[1].trim().equals("")) {
            throw new EmptyTasknumException();
        } else {
            int taskNum = Integer.parseInt(taskNumSplit[1]) - 1;
            return taskNum;
        }
    }

    // Ensure a valid description is provided when adding a new task
    private static String validateDescription(String input, String commandWord) throws EmptyDescriptionException {
        String[] descriptionSplit = input.split(commandWord);
        if (descriptionSplit.length <= 1 || descriptionSplit[1].trim().equals("")) {
            //If task description is empty
            throw new EmptyDescriptionException(commandWord);
        }
        return descriptionSplit[1].trim();
    }

    public static Command parse(String command) throws EmptyTasknumException, EmptyDescriptionException, MissingArgumentException, EmptyStartTimeException, EmptyEndTimeException, EmptyDeadlineException, EmptyDateException, InvalidCommandException {
        String commandWord = command.split(" ")[0].trim();

        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(validateTaskNum(command));
        case "unmark":
            return new UnmarkCommand(validateTaskNum(command));
        case "mark":
            return new MarkCommand(validateTaskNum(command));
        case "todo":
            return new ToDoCommand(validateDescription(command, commandWord));
        case "deadline":
            String descriptionSplit = validateDescription(command, commandWord);
            if (!descriptionSplit.contains(("/by"))) {
                // If /by argument not used
                throw new MissingArgumentException("/by");
            } else {
                String[] deadlineSplit = descriptionSplit.split("/by");
                if (deadlineSplit.length <= 1 || deadlineSplit[1].trim().equals("")) {
                    // If deadline is not given
                    throw new EmptyDeadlineException();
                } else {
                    String taskDescription = deadlineSplit[0].trim();
                    String deadlineString = deadlineSplit[1].trim();
                    return new DeadlineCommand(taskDescription, deadlineString);
                }
            }
        case "event":
            descriptionSplit = validateDescription(command, commandWord);
            if (!descriptionSplit.contains(("/from"))) {
                // If /from argument not used
                throw new MissingArgumentException("/from");
            } else if (!descriptionSplit.contains(("/to"))) {
                // If /to argument not used
                throw new MissingArgumentException("/to");
            } else {
                String[] startTimeSplit = descriptionSplit.split("/from");
                if (startTimeSplit.length <= 1 || startTimeSplit[1].trim().equals("")) {
                    // If start time is not given
                    throw new EmptyStartTimeException();
                } else {
                    String[] endTimeSplit = startTimeSplit[1].split("/to");
                    if (endTimeSplit[0].trim().equals("")) {
                        throw new EmptyStartTimeException();
                    } else if (endTimeSplit.length <= 1 || endTimeSplit[1].trim().equals("")) {
                        // If end time is not given
                        throw new EmptyEndTimeException();
                    } else {
                        String taskDescription = startTimeSplit[0].trim();
                        String startTimeString = endTimeSplit[0].trim();
                        String endTimeString = endTimeSplit[1].trim();

                        return new EventCommand(taskDescription, startTimeString, endTimeString);
                    }
                }
            }
        case "due":
            String[] dueSplit = command.split(commandWord);
            if (dueSplit.length <= 1 || dueSplit[1].trim().equals("")) {
                //If task description is empty
                throw new EmptyDateException();
            } else {
                String dueDateString = dueSplit[1].trim();
                return new DueDateCommand(dueDateString);
            }
        case "bye":
            return new ByeCommand();
        default:
            throw new InvalidCommandException();
        }
    }
}
