package duke;

import duke.ui.Ui;

/**
 * This is the class that parses the command sent to Duke.
 */
public class Parser {

    /**
     * Constructor for the Deadline class.
     */
    public Parser() {
    }

    /**
     * This method checks if the command entered is to exit.
     *
     * @param s The command to check.
     * @return True if the command is to exit.
     */
    public boolean isExit(String s) {
        return s.equalsIgnoreCase("bye");
    }

    public String runCommand(String inputCommand, TaskList tasks, Storage storage, Ui ui) {
        int selectedNum;
        String commandResponse;
        commandResponse = "";

        String[] inputCmdArr = inputCommand.split(" ");
        switch(inputCmdArr[0]) {
        case "list":
            commandResponse+=tasks.returnTaskAsString();
            break;
        case "todo":
            String todoDesc;
            todoDesc = inputCommand.split(" ", 2)[1];
            tasks.addTask(todoDesc, false);
            commandResponse+=tasks.returnNewestTaskAsString();
            storage.save(tasks);
            break;
        case "deadline":
            String deadlineInput;
            deadlineInput = inputCommand.split(" ", 2)[1];
            String[] deadlineDesc = deadlineInput.split(" /by ");
            tasks.addTask(deadlineDesc[0], deadlineDesc[1], false);
            commandResponse+=tasks.returnNewestTaskAsString();
            storage.save(tasks);
            break;
        case "event":
            String eventInput;
            eventInput = inputCommand.split(" ", 2)[1];
            String[] eventDescArr = eventInput.split(" /from ");
            String eventDesc = eventDescArr[0];
            String[] eventTimeArr = eventDescArr[1].split(" /to ");
            String eventFrom = eventTimeArr[0];
            String eventTo = eventTimeArr[1];
            tasks.addTask(eventDesc, eventFrom, eventTo, false);
            commandResponse+=tasks.returnNewestTaskAsString();
            storage.save(tasks);
            break;
        case "mark":
            selectedNum = Integer.parseInt(inputCmdArr[1]);
            commandResponse+=tasks.markTaskWithResult(selectedNum);
            storage.save(tasks);
            break;
        case "unmark":
            selectedNum = Integer.parseInt(inputCmdArr[1]);
            commandResponse+=tasks.unMarkTaskWithResult(selectedNum);
            storage.save(tasks);
            break;
        case "delete":
            int numToDelete;
            numToDelete = Integer.parseInt(inputCommand.split(" ", 2)[1]);
            commandResponse+=tasks.deleteTaskWithResult(numToDelete);
            storage.save(tasks);
            break;
        case "search":
            String searchStr;
            searchStr = inputCommand.split(" ", 2)[1];
            commandResponse+=tasks.searchTaskWithResult(searchStr);
            break;
        default:
            commandResponse+="This command is not supported!";
        }
        return commandResponse;
    }
}
