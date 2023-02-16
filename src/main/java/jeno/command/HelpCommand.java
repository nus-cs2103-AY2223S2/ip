package jeno.command;

import jeno.storage.Note;
import jeno.storage.TaskList;

public class HelpCommand extends Command {
    String helpMessage = "Here is the list of features:\n\n" +
            "todo <task name>\n" +
            "deadline <deadline name> /by <deadline time>*\n" +
            "event <event name> /from <start time> /by <end time>*\n" +
            "delete\n" +
            "clear\n" +
            "list\n" +
            "mark <task index>\n" +
            "unmark <task index>\n" +
            "note <note>\n" +
            "opennotes\n" +
            "clearnotes\n\n" +
            "Note that time has to be in 'DD/MM/YYYY HH:mm' format.";

    /**
     * Constructor for Command class.
     *
     * @param userInput
     */
    public HelpCommand(String userInput) {
        super(userInput);
    }

    public String getCommand(String userInput) {
        return userInput.substring(5);
    }

    @Override
    public String execute(TaskList tasks, Note notes) {
        if (userInput.length() > 4) {
            switch (getCommand(userInput)) {
            case ("todo"):
                return "todo <name> \n\n" +
                        "Add a new todo task of name specified in <name>.\n\n" +
                        "E.g. 'todo read book'";
            case("deadline"):
                return "deadline <name> /by <time>\n\n" +
                        "Add a new deadline of name specified in <name> " +
                        "and deadline specified in <time>.\n\n" +
                        "E.g. 'deadline return book /by 30/03/2023 15:00'";
            case("event"):
                return "event <name> /from <start time> /to <end time>\n\n" +
                        "Add a new event of name specified in <name>, " +
                        "start time specified in <start time>, and " +
                        "end time specified in <end time>.\n\n" +
                        "E.g. 'event CS2103T Tutorial /from 16/02/2023 15:00 /to 16/02/2023 16:00";
            case("delete"):
                return "delete <task index> \n\n" +
                        "Delete task with index specified in <task index> from task list.\n" +
                        "To view current task list, type in 'list'";
            case("clear"):
                return "clear \n\n" +
                        "Clear all active tasks from task list.";
            case("list"):
                return "list\n\n" +
                        "List all active tasks in task list.";
            case("mark"):
                return "mark <task index>\n\n" +
                        "Mark a task of index specified in <task index> as done.\n";
            default:
            }
        }
        return helpMessage;
    }
}
