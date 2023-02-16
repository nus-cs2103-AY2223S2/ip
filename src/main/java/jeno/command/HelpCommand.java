package jeno.command;

import jeno.exception.JenoException;
import jeno.storage.Note;
import jeno.storage.TaskList;

public class HelpCommand extends Command {
    String helpMessage = "Here is the list of features:\n\n" +
            "todo <task name>\n" +
            "deadline <deadline name> /by <deadline time>*\n" +
            "event <event name> /from <start time> /by <end time>*\n" +
            "find <keyword>\n" +
            "delete\n" +
            "clear\n" +
            "list\n" +
            "mark <task index>\n" +
            "unmark <task index>\n" +
            "note <note>\n" +
            "opennotes\n" +
            "clearnotes\n\n" +
            "Note that time has to be in 'DD/MM/YYYY HH:mm' format.\n\n" +
            "For detailed command description, type in 'help <command name>'\n" +
            "E.g. 'help todo'";

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
    public String execute(TaskList tasks, Note notes) throws JenoException {
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
                        "Mark a task of index specified in <task index> as done.";
            case("unmark"):
                return "unmark <task index>\n\n" +
                        "Unmark a task of index specified in <task index>.";
            case("find"):
                return "find <keyword>\n\n" +
                        "Display all tasks in current task list that contains keyword.";
            case("note"):
                return "note <note>\n\n" +
                        "Store note inputted in <note> field.";
            case("opennotes"):
                return "opennotes\n\n" +
                        "Display current notes.";
            case("clearnotes"):
                return "clearnotes\n\n" +
                        "Delete all notes.";
            default:
                if(!getCommand(userInput).isBlank()) {
                    throw new JenoException("Oops! Please enter a valid command.");
                }
            }
        }
        return helpMessage;
    }
}
