package iris.command;

import iris.TaskList;
import iris.TaskStore;

/**
 * displays the help message with all commands
 */
public class HelpCommand extends Command {
    /**
     * {@inheritDoc}
     */
    private static final String HELP_TEXT = "Hello! You can use the following commands:\n"
            + "To add a todo task, type \"todo \" + your task.\n"
            + "To add a task with a deadline, type \"deadline \" + your task + \"/by \" + your deadline.\n"
            + "To add an event, type \"event \" + your event + "
            + "\"/from \" + event start time + \"/to \" + event end time.\n"
            + "For deadlines and events, the date and time should by in the format "
            + "\"dd-MM-yyyy HHmm\". Eg: 15-05-2019 1800.\n"
            + "To see an list of your tasks, type \"list\".\n"
            + "To mark a task as done, type \"mark <task number>\".\n"
            + "To mark a task as not done, type \"unmark <task number>\".\n"
            + "To delete a task, type \"delete <task number>\".\n"
            + "A task marked with a X is done.\n"
            + "To reset the task list type \"reset\".\n"
            + "To get a list of deadlines and events on a particular day, type \"filter \" + date (dd-MM-yyyy).\n"
            + "To get a list of deadlines and events during a period of time, type "
            + "\"filter \" + start of period \" /to \" end of period (dd-MM-yyyy HHmm).\n"
            + "To find a task, type \"find <keywords>\".\n"
            + "To close me, type \"bye\".\n"
            + "Have fun!";

    @Override
    public String getResponse(TaskList tasks, TaskStore taskStore) {
        return HELP_TEXT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof HelpCommand;
    }
}
