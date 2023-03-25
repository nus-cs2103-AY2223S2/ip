package brotherbot.commands;

import brotherbot.storage.TaskList;

public class HelpCommand extends Command {

    /**
     * Constructor to create an HelpCommand object.
     *
     * @param input Input string required for command execution.
     */
    public HelpCommand(String input) {
        super(input);
    }

    /**
     * Executes command.
     *
     * @param storage Existing TaskList object required for command execution.
     */
    public String execute(TaskList storage) {
        String output = " Here is a list of commands:\n" +
                "Add To-Do item: todo task_name\nAdd Deadline: deadline task_name /by dd/MM/yyyy HHmm\n" +
                "Add Event: event task_name /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm\nDisplay Tasks: display\n" +
                "Mark Task as Done: mark task_number\nDelete Task: delete task_number\nFind Tasks: find keyword\n" +
                "Find Next Freeslot: free xHyyM 'note: where x is no. of hrs, yy is no. of min'\nExit: bye ";
        return output;
    }
}


