package james.command;

import james.exception.JamesException;
import james.jamesbot.Storage;
import james.jamesbot.Ui;
import james.task.Event;
import james.task.TaskList;

/**
 * Adds a task of type Event to the task list.
 */
public class AddEventCommand extends Command {
    public static final String COMMAND = "event";

    public static final String MESSAGE = COMMAND + ": Adds a task of type event.\n"
            + "(e.g event project meeting \n"
            + "/from 25/03/2000 1800 /to 25/03/2000 1900)";

    public static final String MESSAGE_FORMAT = "Please follow the format for event task:"
            + "\n'event [task description] /from [d/MM/yyyy HHmm] /to [d/MM/yyyy HHmm]'\n"
            + "Example: 'event project meeting \n"
            + "/from 25/03/2000 1800 /to 25/03/2000 1900' ";

    private String userInput;

    /**
     * Constructs an AddEventCommand object.
     *
     * @param userInput The input the user typed.
     */
    public AddEventCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the AddEventCommand which adds a task of type Event into a stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out response from JamesBot.
     * @param storage The task list that is stored in the storage file.
     * @throws JamesException If description of task is empty;
     *                        If descriptor is not present;
     *                        If event timing is empty.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamesException {
        boolean isDescriptionEmpty = userInput.toLowerCase().replaceFirst(COMMAND, "").isBlank();
        if (isDescriptionEmpty) {
            throw new JamesException("Task description is empty! \n"
                    + MESSAGE_FORMAT);
        }

        int commandLength = COMMAND.length();
        String taskInformation = userInput.substring(commandLength).trim();
        String descriptor1 = "/from";
        String descriptor2 = "/to";
        boolean hasNoDescriptor1 = !taskInformation.contains(descriptor1);
        boolean hasNoDescriptor2 = !taskInformation.contains(descriptor2);
        boolean hasNoDateFrom = taskInformation.trim().endsWith(descriptor1);
        boolean hasNoDateTo = taskInformation.trim().endsWith(descriptor2);

        if (hasNoDescriptor1 || hasNoDescriptor2) {
            throw new JamesException("Task description missing descriptor: "
                    + descriptor1 + " "
                    + descriptor2 + " \n"
                    + MESSAGE_FORMAT);
        }

        if (hasNoDateFrom || hasNoDateTo) {
            throw new JamesException("Task description missing date\n"
                    + MESSAGE_FORMAT);
        }

        int startIndexEvent = userInput.indexOf(descriptor1 + " ");
        int endIndexEvent = userInput.indexOf(descriptor2 + " ");
        int userCmdLenEvent = userInput.length();
        String description = userInput.substring(commandLength, startIndexEvent).trim();

        boolean hasNoDescription = description.isBlank();


        if (hasNoDescription) {
            throw new JamesException("your task description is empty\n"
                    + MESSAGE_FORMAT);
        }
        String startEvent = userInput.substring(startIndexEvent + 5, endIndexEvent).trim();
        String endEvent = userInput.substring(endIndexEvent + 3, userCmdLenEvent).trim();
        Event taskEvent = new Event(description, startEvent, endEvent);
        tasks.add(taskEvent);
        storage.save(tasks.taskListToStoreString());
        return ui.displayAddTask(taskEvent, tasks.size());
    }


    /**
     * Returns whether AddEventCommand exits the program.
     *
     * @return false as AddEventCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
