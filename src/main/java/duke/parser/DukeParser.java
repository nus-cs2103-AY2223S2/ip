package duke.parser;

import duke.command.DukeCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class DukeParser {
    String inputString;
    DukeCommand command;

    final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DukeParser(String inputString) {
        this.inputString = inputString;
    }

    public DukeCommand getCommand() {
        return this.command;
    }

    /**
     * Parses the {@code inputString} field then return the command and arguments.
     * 
     * @return {@code String[]} the command arguments needed for the command.
     */
    public String[] parse() {
        parseCommand();
        return parseCommandArgs();
    }

    private void parseCommand() {
        // First split by space to get each individual word of the inputString
        String[] splittedString = inputString.split(" ");

        // The first(0th) word has to be a command.
        String command = splittedString[0];

        // Check whether the command is valid.
        for (DukeCommand dukeCmd : DukeCommand.values()) {
            if (dukeCmd.text.equals(command)) {
                this.command = dukeCmd;
                return;
            }
        }
        // If command is invalid:
        throw new Error("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private String[] parseCommandArgs() {
        List<String> commandArgs = new ArrayList<>();
        switch (command) {
        case LIST:
        case BYE:
            break;

        case DEADLINE: {
            int offset = DukeCommand.DEADLINE.text.length();
            int byIndex = inputString.indexOf("/by", offset);
            boolean isByKeywordExist = byIndex != -1;
            if (!isByKeywordExist)
                throw new Error("Invalid argument!");
            String description = inputString.substring(offset, byIndex).strip();
            String deadline = inputString.substring(byIndex + ("/by".length())).strip();


            try {
                LocalDate.parse(deadline, INPUT_DATE_FORMAT);
                commandArgs.add(description);
                commandArgs.add(deadline);
            } catch (DateTimeParseException e) {
                throw new Error("☹ OOPS!!! The time format is invalid, please use yyyy-MM-dd");
            }
            break;
        }
        case EVENT: {
            int offset = DukeCommand.DEADLINE.text.length();
            int fromIndex = inputString.indexOf("/from", offset);
            int toIndex = inputString.indexOf("/to", offset);

            boolean isKeywordExist = fromIndex != -1 && toIndex != -1;
            if (!isKeywordExist)
                throw new Error("Invalid argument!");

            String description = inputString.substring(offset, fromIndex).strip();

            try {
                String from =
                        inputString.substring(fromIndex + ("/from".length()), toIndex).strip();
                String to = inputString.substring(toIndex + ("/to".length())).strip();
                LocalDate fromDate = LocalDate.parse(from, INPUT_DATE_FORMAT);
                LocalDate toDate = LocalDate.parse(to, INPUT_DATE_FORMAT);

                boolean isValidFromDate = fromDate.isBefore(toDate) || fromDate.isEqual(toDate);

                if (!isValidFromDate)
                    throw new Error("☹ OOPS!!! from date should be before to date!");
                commandArgs.add(description);
                commandArgs.add(from);
                commandArgs.add(to);

            } catch (DateTimeParseException e) {
                throw new Error("☹ OOPS!!! The time format is invalid, please use yyyy-MM-dd");
            }
            break;
        }

        case TODO: {
            String description = inputString.substring(DukeCommand.TODO.text.length());
            String cleanedDescription = description.strip();

            boolean isDescriptionEmpty = cleanedDescription.length() == 0;
            if (isDescriptionEmpty) {
                throw new Error("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            commandArgs.add(cleanedDescription);
            break;
        }

        case DELETE: {
            String taskIndex = inputString.substring(DukeCommand.DELETE.text.length());
            String cleanedTaskIndex = taskIndex.strip();
            try {
                Integer.parseInt(cleanedTaskIndex);
                commandArgs.add(cleanedTaskIndex);
            } catch (NumberFormatException e) {
                throw new Error("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            break;
        }
        case MARK: {
            String taskIndex = inputString.substring(DukeCommand.MARK.text.length());
            String cleanedTaskIndex = taskIndex.strip();
            try {
                Integer.parseInt(cleanedTaskIndex);
                commandArgs.add(cleanedTaskIndex);
            } catch (NumberFormatException e) {
                throw new Error("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            break;
        }
        case UNMARK: {
            String taskIndex = inputString.substring(DukeCommand.UNMARK.text.length());
            String cleanedTaskIndex = taskIndex.strip();
            try {
                Integer.parseInt(cleanedTaskIndex);
                commandArgs.add(cleanedTaskIndex);
            } catch (NumberFormatException e) {
                throw new Error("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            break;
        }
        }
        return commandArgs.toArray(new String[] {});
    }
}
