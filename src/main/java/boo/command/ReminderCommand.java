package boo.command;

import boo.tasklist.TaskList;

/**
 * Represents a "reminder" command that is entered by the user.
 */
public class ReminderCommand extends Command {

    /** The period within which the tasks must fall to be reminded */
    private String reminderDuration;

    /**
     * Constructs a {@code ReminderCommand}.
     *
     * @param reminderDuration The duration to check for tasks that are either going to be due or upcoming.
     * @param tasks The tasklist that contains all the tasks to look through.
     */
    public ReminderCommand(String reminderDuration, TaskList tasks) {
        super();
        this.reminderDuration = reminderDuration;

    }

    /**
     * Returns the list of tasks that are either due or upcoming within the given reminder time duration.
     * For example, if the reminder time duration is 1 day, and we let reminderDate be 1 day from the current time
     * this method is called, then all deadlines due on or before reminderDate and all events that either end or span
     * over reminderDate will be printed out.
     *
     * @return the list of supported commands
     */
    @Override
    public String runCommand() {
        //TODO: Fill in body for reminder command
    }
}
