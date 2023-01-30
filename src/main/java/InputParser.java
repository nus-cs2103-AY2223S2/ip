import java.util.ArrayList;
import java.util.Arrays;

public class InputParser {

    private final static String[] possibleCommandsArr = {
            "bye", "list", "todo", "deadline", "event", "mark", "unmark"
    };


    public enum CommandType {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, ERROR;
    }

    private CommandType command;

    private String[] inputArguments;

    InputParser(InputError ie) {
        this.command = CommandType.ERROR;
        String[] tempArr = {ie.getMessage()};
        this.inputArguments = tempArr;
    }

    InputParser(String userInput) throws InputError {

        ArrayList<String> possibleCommandsList = new ArrayList<>();
        possibleCommandsList.addAll(Arrays.asList(this.possibleCommandsArr));
        if (!userInput.contains(" ")) { // handling simple commands (one word)
            if (userInput.equals("bye")) {
                this.command = CommandType.BYE;
            } else if (userInput.equals("list")) {
                this.command = CommandType.LIST;
            } else if (possibleCommandsList.contains(userInput)) { // no args for valid command
                throw new InputError("this command cannot have an empty description!");
            } else { // unknown command
                throw new InputError("i don't understand this command!");
            }
            // below handles commands with arguments
        } else if (userInput.startsWith("todo ")) {
            this.command = CommandType.TODO;
            this.inputArguments = new String[1];
            this.inputArguments[0] = userInput.substring(5, userInput.length());
        } else if (userInput.startsWith("deadline ")) {
            this.command = CommandType.DEADLINE;
            this.inputArguments = new String[2];
            String argString = userInput.substring(9,userInput.length());
            if (argString.contains(" /by ")) {
                this.inputArguments = argString.split(" /by ", 2);
            } else {
                throw new InputError("command should follow the format:\n" +
                        "deadline *description* /by *timing*");
            }
        } else if (userInput.startsWith("event ")) {
            this.command = CommandType.EVENT;
            this.inputArguments = new String[3];
            String argString = userInput.substring(6, userInput.length());
            if (argString.contains(" /from ") && argString.contains(" /to ")) {
                String[] tempArr = argString.split(" /from ", 2);
                String[] tempArr2 = tempArr[1].split(" /to ");
                this.inputArguments[0] = tempArr[0];
                this.inputArguments[1] = tempArr2[0];
                this.inputArguments[2] = tempArr2[1];
            } else {
                throw new InputError("command should follow the format:\n" +
                        "event *description* /from *start time* /to *end time*");
            }
        } else if (userInput.startsWith("mark ")) {
            this.command = CommandType.MARK;
            try {
                Integer.valueOf(userInput.substring(5, userInput.length()));
                this.inputArguments = new String[1];
                this.inputArguments[0] = userInput.substring(5, userInput.length());
            } catch (NumberFormatException nfe) {
                throw new InputError("your argument has to be an integer! (e.g: mark 2)");
            }
        } else if (userInput.startsWith("unmark ")) {
            this.command = CommandType.UNMARK;
            try {
                Integer.valueOf(userInput.substring(7, userInput.length()));
                this.inputArguments = new String[1];
                this.inputArguments[0] = userInput.substring(7, userInput.length());
            } catch (NumberFormatException nfe) {
                throw new InputError("your argument has to be an integer! (e.g: unmark 2)");
            }
        } else if (userInput.startsWith("delete ")) {
            this.command = CommandType.DELETE;
            try {
                Integer.valueOf(userInput.substring(7, userInput.length()));
                this.inputArguments = new String[1];
                this.inputArguments[0] = userInput.substring(7, userInput.length());
            } catch (NumberFormatException nfe) {
                throw new InputError("your argument has to be an integer! (e.g: delete 2");
            }
        } else {
            throw new InputError("i don't understand this command!");
        }
    }

    public String[] getArguments() {
        return this.inputArguments;
    }

    public CommandType getCommandType() {
        return this.command;
    }
}
