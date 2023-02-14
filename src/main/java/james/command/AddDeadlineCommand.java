package james.command;

import james.exception.JamesException;
import james.jamesbot.Storage;
import james.jamesbot.Ui;
import james.task.TaskList;
import james.task.Deadline;



/**
 * Adds a task of type Deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND = "deadline";

    public static final String MESSAGE = COMMAND + ": adds a task of type deadline.\n"
            + "(e.g deadline lab assignment /by 25/03/2000 1800)";

    public static final String MESSAGE_FORMAT = "add a deadline task using the following format:\n"
            + "'deadline [task description] /by [date and time]'\n"
            + "make sure that your [date and time] is of the format: d/MM/yyyy HHmm\n"
            + "here is an example, 'deadline project /by 25/03/2000 1800";

    private String userCommand;

    /**
     * Constructs an AddDeadlineCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public AddDeadlineCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the AddDeadlineCommand which adds a task of type Deadline into a stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out JamesBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws JamesException If task description is empty;
     *                      If task does not contain descriptor;
     *                      If date is empty for deadline task.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamesException {
        boolean isTaskDescriptionEmpty = userCommand.toLowerCase().replaceFirst(COMMAND, "").isBlank();
        if (isTaskDescriptionEmpty) {
            throw new JamesException("Task description is empty!\n"
                    + MESSAGE_FORMAT);
        }

        int commandLength = COMMAND.length();
        String taskInformation = userCommand.substring(commandLength).trim();
        String descriptor = "/by";
        boolean hasNoDescriptor = !taskInformation.contains(descriptor);
        boolean hasNoDate = taskInformation.trim().endsWith(descriptor);

        if (hasNoDescriptor) {
            throw new JamesException("Task description missing descriptor: "
                    + descriptor + "\n"
                    + MESSAGE_FORMAT);
        }

        if (hasNoDate) {
            throw new JamesException("Task description missing date!\n"
                    + MESSAGE_FORMAT);
        }

        int deadlineIndex = userCommand.indexOf(descriptor + " ");
        String description = userCommand.substring(commandLength, deadlineIndex).trim();
        boolean hasNoDescription = description.isBlank();

        if (hasNoDescription) {
            throw new JamesException("Task description is missing! \n"
                    + MESSAGE_FORMAT);
        }

        String deadline = userCommand.substring(deadlineIndex + 3).trim();

        Deadline task = new Deadline(description, deadline);
        tasks.add(task);
        storage.save(tasks.taskListToStoreString());
        return ui.displayAddTask(task, tasks.size());

    }

    /**
     * Returns whether AddDeadlineCommand exits the program.
     *
     * @return false as AddDeadlineCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
