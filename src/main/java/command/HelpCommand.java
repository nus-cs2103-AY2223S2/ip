package command;

import main.Storage;
import main.TaskList;
import main.Ui;

public class HelpCommand implements Command{
    private static final String ALL = "all";
    private String command;
    
    public HelpCommand(String command) {
        this.command = command.toLowerCase();
    }

    public HelpCommand() {
        this.command = ALL;
    }
    
    /**
     * Executes the task when user inputs "list"
     * 
     * @param list    the list in duke to store the user's Task
     * @param ui      the user interface that is present in duke
     * @param storage the storage to store the task in the TaskList list
     * @throws DukeException if there's an error
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        switch (command) {
            case ("bye"):
                ui.appendToOutput(generateByeMessage());
            case ("deadline"):
                ui.appendToOutput(generateDeadlineMessage());
            case ("delete"):
                ui.appendToOutput(generateDeleteMessage());
            case ("mark"):
                ui.appendToOutput(generateMarkMessage());
            case ("unmark"):
                ui.appendToOutput(generateUnmarkMessage());
            case ("event"):
                ui.appendToOutput(generateEventMessage());
            case ("find"):
                ui.appendToOutput(generateFindMessage());
            case ("list"):
                ui.appendToOutput(generateListMessage());
            case ("todo"):
                ui.appendToOutput(generateTodoMessage());
            case ("help"):
                ui.appendToOutput(generateHelpMessage());
            default:
                ui.appendToOutput(generateAllMessage());
            }
    }

    public String generateTodoMessage() {
        return "todo command\n"
                + "    Create a todo task.\n"
                + "    Format: todo <task name>\n"
                + "    Eg. todo homework1\n";
    }

    public String generateEventMessage() {
        return "event command\n"
                + "    Create an event task.\n"
                + "    Format: event <task name> /from <date>\n"
                + "    Eg. event meeting1 /at 04/10/2000\n"
                + "    <date> MUST be in dd/mm/yyyy HHmm\n";
    }

    public String generateDeadlineMessage() {
        return "deadline command\n"
                + "    Create a deadline task.\n"
                + "    Format: deadline <task name> /by <date>\n"
                + "    Eg. deadline meeting1 /by 04/10/2000\n"
                + "    <date> MUST be in dd/mm/yyyy HHmm\n";
    }

    public String generateByeMessage() {
        return "bye command\n"
                + "    Stops this programme.\n";
    }
    
    public String generateDeleteMessage() {
        return "delete command\n"
                + "    Deletes the task specified\n"
                + "    Format: delete <task number>\n"
                + "    Eg. delete 3\n"
                + "    <task number> is obtained by listing tasks\n";
    }

    public String generateMarkMessage() {
        return "Mark command\n"
                + "    Marks the task specified as done\n"
                + "    Format: done <task number>\n"
                + "    Eg. mark 3\n"
                + "    <task number> is obtained by listing tasks\n";
    }

    public String generateUnmarkMessage() {
        return "Unmark command\n"
                + "    Unmarks the task specified as done\n"
                + "    Format: unmark <task number>\n"
                + "    Eg. unmark 3\n"
                + "    <task number> is obtained by listing tasks\n";
    }

    public String generateListMessage() {
        return "list command\n"
                + "    Lists all tasks out in order of creation\n"
                + "    Format: list\n"
                + "    Eg. list\n";
    }

    public String generateHelpMessage() {
        return "help command\n"
                + "    Prints guide for commands.\n"
                + "    Format: help <command> , help\n"
                + "    Eg. help, help todo\n";
    }

    public String generateFindMessage() {
        return "find command\n"
                + "    Lists all tasks that match a given search term\n"
                + "    Format: find <search term>\n"
                + "    Eg. find homework\n"
                + "    This is a case insensitive search and does a\n"
                + "    keyword search on all task names.\n";
    }

    public String generateAllMessage() {
        return generateByeMessage() + "\n"
                + generateDeadlineMessage() + "\n"
                + generateDeleteMessage() + "\n"
                + generateMarkMessage() + "\n"
                + generateUnmarkMessage() + "\n"
                + generateEventMessage() + "\n"
                + generateFindMessage() + "\n"
                + generateHelpMessage() + "\n"
                + generateListMessage() + "\n"
                + generateTodoMessage();
    }

    /**
     * Checks whether the task exits the programme
     * 
     * @return true if this command exits the programme, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
