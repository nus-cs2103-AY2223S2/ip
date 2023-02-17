package james.command;

import james.exception.JamesException;
import james.jamesbot.Storage;
import james.jamesbot.Ui;
import james.task.Deadline;
import james.task.TaskList;




/**
 * Adds a task of type Deadline to the task list.
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND = "deadline";

    public static final String MESSAGE = COMMAND + ": Adds a task of type deadline.\n"
            + "(e.g deadline project /by 25/03/2000 1800)";

    public static final String MESSAGE_FORMAT = "Please follow the format for deadline:\n"
            + "'deadline [description] /by [d/MM/yyyy] HHmm'\n"
            + "Example: 'deadline project /by 25/03/2000 1800' ";

    private String userInput;

    /**
     * Constructs an AddDeadlineCommand object.
     *
     * @param userInput The input the user typed.
     */
    public AddDeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the AddDeadlineCommand which adds a task of type Deadline into a stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out response from JamesBot.
     * @param storage The task list that is stored in the storage file.
     * @throws JamesException If description of task is empty;
     *                        If descriptor is not present;
     *                        If deadline timing is empty.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamesException {
        boolean isDescriptionEmpty = userInput.toLowerCase().replaceFirst(COMMAND, "").isBlank();
        if (isDescriptionEmpty) {
            throw new JamesException("Task description is empty!\n"
                    + MESSAGE_FORMAT);
        }

        int commandLength = COMMAND.length();
        String taskInformation = userInput.substring(commandLength).trim();
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

        int deadlineIndex = userInput.indexOf(descriptor + " ");
        String description = userInput.substring(commandLength, deadlineIndex).trim();

        boolean hasNoDescription = description.isBlank();

        if (hasNoDescription) {
            throw new JamesException("Task description is missing! \n"
                    + MESSAGE_FORMAT);
        }

        String deadline = userInput.substring(deadlineIndex + 3).trim();

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
