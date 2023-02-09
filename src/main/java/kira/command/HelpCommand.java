package kira.command;

import kira.exception.KiraException;
import kira.storage.TaskList;
import kira.ui.Ui;

/**
 * Command for HELP.
 */
public class HelpCommand extends Command {

    private CommandString command;
    private StringBuilder sBuilder = new StringBuilder();

    /**
     * Constructs an executable to provide help information for
     * the user
     *
     * @param command
     */
    public HelpCommand(String command) throws KiraException {
        try {
            this.command = CommandString.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new KiraException("Command not recognised! Unable to help you...");
        }
    }

    public HelpCommand() {
        this.command = CommandString.HELP;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasklist) {
        String msg = "";
        switch (command) {
        case BYE:
            msg = byeHelpMsg();
            break;
        case DEADLINE:
            msg = deadlineHelpMsg();
            break;
        case DELETE:
            msg = deleteHelpMsg();
            break;
        case EVENT:
            msg = eventHelpMsg();
            break;
        case FIND:
            msg = findHelpMsg();
            break;
        case LIST:
            msg = listHelpMsg();
            break;
        case MARK:
            msg = markHelpMsg();
            break;
        case TODAY:
            msg = todayHelpMsg();
            break;
        case TODO:
            msg = todoHelpMsg();
            break;
        case UNMARK:
            msg = unmarkHelpMsg();
            break;
        case HELP:
            msg = helpHelpMsg();
            break;
        default:
            assert false : command;
            break;
        }
        ui.helpMsg(msg);
        return true;
    }

    private String unmarkHelpMsg() {
        sBuilder.append("=== Unmark command ===\n")
                .append("Format: unmark <index>\n")
                .append("Index - Index of the task shown in list\n")
                .append("----------------------\n")
                .append("Unmarks the task at <index> in the list");
        return sBuilder.toString();
    }

    private String todoHelpMsg() {
        sBuilder.append("=== Todo command ===\n")
                .append("Format: todo <description>\n")
                .append("Description - The task description\n")
                .append("----------------------\n")
                .append("Adds a simple task to be done");
        return sBuilder.toString();
    }

    private String todayHelpMsg() {
        sBuilder.append("=== Today command ===\n")
                .append("Format: today\n")
                .append("----------------------\n")
                .append("Finds out all the tasks ongoing for today");
        return sBuilder.toString();
    }

    private String markHelpMsg() {
        sBuilder.append("=== Mark command ===\n")
                .append("Format: mark <index>\n")
                .append("Index - Index of the task shown in list\n")
                .append("----------------------\n")
                .append("Marks the task at <index> in the list");
        return sBuilder.toString();
    }

    private String listHelpMsg() {
        sBuilder.append("=== List command ===\n")
                .append("Format: list\n")
                .append("----------------------\n")
                .append("Lists all the tasks that have been stored");
        return sBuilder.toString();
    }

    private String helpHelpMsg() {
        sBuilder.append("=== Help command ===\n")
                .append("Format: help <command>\n")
                .append("Command - Command that you need help with\n")
                .append("----------------------\n")
                .append("Shows you the format and what the command do");
        return sBuilder.toString();
    }

    private String findHelpMsg() {
        sBuilder.append("=== Find command ===\n")
                .append("Format: find <keyword>\n")
                .append("Keyword - The keyword you want to search for\n")
                .append("----------------------\n")
                .append("Finds all the tasks related to the keyword");
        return sBuilder.toString();
    }

    private String eventHelpMsg() {
        sBuilder.append("=== Event command ===\n")
                .append("Format: event <Description> /from <From Date> /to <To Date>\n")
                .append("Description - The task description\n")
                .append("From Date - Starting date in yyyy-MM-dd HHmm")
                .append("To Date - Ending date in yyyy-MM-dd HHmm")
                .append("----------------------\n")
                .append("Adds an event that has a starting date and ending date");
        return sBuilder.toString();
    }

    private String deleteHelpMsg() {
        sBuilder.append("=== Delete command ===\n")
                .append("Format: delete <Index>\n")
                .append("Index - Index of the task shown in list\n")
                .append("----------------------\n")
                .append("Removes a task specified by the index from the list");
        return sBuilder.toString();
    }

    private String deadlineHelpMsg() {
        sBuilder.append("=== Deadline command ===\n")
                .append("Format: deadline <Description> /by <Date>\n")
                .append("Description - The task description\n")
                .append("From Date - Starting date in yyyy-MM-dd HHmm")
                .append("----------------------\n")
                .append("Adds a task with a set deadline");
        return sBuilder.toString();
    }

    private String byeHelpMsg() {
        sBuilder.append("=== Bye command ===\n")
                .append("Format: bye\n")
                .append("----------------------\n")
                .append("Initiates KiraBot shutdown sequence... zzz");
        return sBuilder.toString();
    }

}
