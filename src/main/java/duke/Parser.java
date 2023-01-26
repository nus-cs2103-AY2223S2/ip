package duke;

public class Parser {

    public void parse(String userInput, TaskList taskList
            , Storage storage, TextUi textUi) throws DukeException {

        String command;
        String body;

        if (userInput.contains(" ")) {
            command = userInput.substring(0, userInput.indexOf(" "));
            body = userInput.substring(userInput.indexOf(" ") + 1);
        } else {
            command = userInput;
            body = "";
        }

        switch (command) {
            case "list":
                Commands.executeListCommand(textUi, taskList);
                break;
            case "mark":
                Commands.executeMarkCommand(body, textUi, taskList, storage);
                break;
            case "unmark":
                Commands.executeUnmarkCommand(body, textUi, taskList, storage);
                break;
            case "delete":
                Commands.executeDeleteCommand(body, textUi, taskList, storage);
                break;
            case "todo":
                Commands.executeToDoCommand(body, textUi, taskList, storage);
                break;
            case "deadline":
                Commands.executeDeadlineCommand(body, textUi, taskList, storage);
                break;
            case "event":
                Commands.executeEventCommand(body, textUi, taskList, storage);
                break;
            default:
                throw new DukeException("I'm sorry, I don't know what that means!");
        }
    }

    public static String removeWhiteSpaces(String str) {
        return str.replaceAll("\\s", "");
    }
}