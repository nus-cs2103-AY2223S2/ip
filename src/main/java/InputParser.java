import java.util.ArrayList;
import java.util.Arrays;

public class InputConversion {

    private final static String[] possibleCommandsArr = {
            "bye", "list", "todo", "deadline", "event", "mark", "unmark"
    };


    enum commandType {
        bye, list, todo, deadline, event, mark, unmark
    }

    private commandType command;

    private String[] inputArguments;

    InputConversion(String userInput) throws InputError {

        ArrayList<String> possibleCommandsList = new ArrayList<String>();
        possibleCommandsList.addAll(Arrays.asList(this.possibleCommandsArr));
        if (!userInput.contains(" ")) { // handling simple command (one word)
            if (userInput == "bye") {
                this.command = commandType.bye;
            } else if (userInput == "list") {
                this.command = commandType.list;
            } else if (possibleCommandsList.contains(userInput)) { // no args for valid command
                throw new InputError("no arguments given");
            } else { // unknown command
                throw new InputError("unknown command");
            } // handle commands with arguments
        } else if (userInput.startsWith("todo ")) {
            this.command = commandType.todo;
            this.inputArguments = new String[1];
            this.inputArguments[0] = userInput.substring(5, userInput.length());
        } else if (userInput.startsWith("deadline ")) {
            this.command = commandType.deadline;
            this.inputArguments = new String[2];
            String argString = userInput.substring(9,userInput.length());
            if (argString.contains(" /by ")) {
                this.inputArguments = argString.split(" /by ", 2);
            } else {
                throw new InputError("syntax error");
            }
        } else if (userInput.startsWith("event ")) {
            this.command = commandType.event;
            this.inputArguments = new String[3];
            String argString = userInput.substring(6, userInput.length());
            if (argString.contains(" /from ") && argString.contains(" /to ")) {
                String[] tempArr = argString.split(" /from ", 2);
                String[] tempArr2 = tempArr[1].split();
                this.inputArguments[0] = tempArr[0];
                this.inputArguments[1] = tempArr2[0];
                this.inputArguments[2] = tempArr2[1];
            } else {
                throw new InputError("syntax error");
            }
        } else if (userInput.startsWith("mark ")) {
            this.command = commandType.mark;
            try {
                Integer.valueOf(userInput.substring(5, userInput.length()));
                this.inputArguments = new String[1];
                this.inputArguments[0] = userInput.substring(5, userInput.length());
            } catch (NumberFormatException nfe) {
                throw new InputError("wrong argument error");
            }
        } else if (userInput.startsWith("unmark ")) {
            this.command = commandType.unmark;
            try {
                Integer.valueOf(userInput.substring(5, userInput.length()));
                this.inputArguments = new String[1];
                this.inputArguments[0] = userInput.substring(7, userInput.length());
            } catch (NumberFormatException nfe) {
                throw new InputError("wrong argument error");
            }
        }
    }

    public String[] getArguments() {
        return this.inputArguments;
    }

    public commandType getCommandType() {
        return this.command;
    }
}
