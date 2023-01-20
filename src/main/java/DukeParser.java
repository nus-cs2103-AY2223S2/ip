import java.util.ArrayList;
import java.util.List;

public class DukeParser {
    String inputString;
    DukeCommand command;

    public DukeParser(String inputString) {
        this.inputString = inputString;
    }

    public DukeCommand getCommand() {
        return this.command;
    }

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
                boolean isByKeyordExist = byIndex != -1;
                if (!isByKeyordExist)
                    throw new Error("Invalid argument!");
                String description = inputString.substring(offset, byIndex).strip();
                String deadline = inputString.substring(byIndex + ("/by".length())).strip();

                commandArgs.add(description);
                commandArgs.add(deadline);
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
                String from =
                        inputString.substring(fromIndex + ("/from".length()), toIndex).strip();
                String to = inputString.substring(toIndex + ("/to".length())).strip();

                commandArgs.add(description);
                commandArgs.add(from);
                commandArgs.add(to);
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
