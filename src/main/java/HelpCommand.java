public class HelpCommand extends Command {
    private static final String HELP_TEXT = "Hello! You can use the following commands:\n" +
            "To add a todo task, type \"todo \" + your task.\n" +
            "To add a task with a deadline, type \"deadline \" + your task + \"/by \" + your deadline.\n" +
            "To add an event, type \"event \" + your event + " +
            "\"/from \" + event start time + \"/to \" + event end time.\n" +
            "To see an list of your tasks, type \"list\".\n" +
            "To mark a task as done, type \"mark <task number>\".\n"  +
            "To mark a task as not done, type \"unmark <task number>\".\n"  +
            "To delete a task, type \"delete <task number>\".\n" +
            "A task marked with a X is done.\n" +
            "To reset the task list type \"reset\".\n" +
            "To close me, type \"bye\".\n" +
            "Have fun!";

    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) {
        Ui.output(HELP_TEXT);
    }
}
