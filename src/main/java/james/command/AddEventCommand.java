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

    public static final String MESSAGE = COMMAND + ": adds a task of type event.\n"
            + "(e.g event project meeting \n"
            + "/from 25/03/2000 1800 /to 25/03/2000 1900)";

    public static final String MESSAGE_FORMAT = "add an event task using the following format:"
            + "\n'event [task description] /from [date and time] /to [date and time]'\n"
            + "make sure that your [date and time] is of the format: d/MM/yyyy HHmm\n"
            + "here is an example, 'event christmas party \n"
            + "/from 25/03/2000 1800 /to 25/03/2000 1900";

    private String userCommand;

    /**
     * Constructs an AddEventCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public AddEventCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Executes the AddEventCommand which adds a task of type Event into a stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out JamesBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws JamesException If task description is empty;
     *                      If task does not contain descriptor;
     *                      If date is empty for event task.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamesException {
        boolean isTaskDescriptionEmpty = userCommand.toLowerCase().replaceFirst(COMMAND, "").isBlank();
        if (isTaskDescriptionEmpty) {
            throw new JamesException("Task description is empty! \n"
                    + MESSAGE_FORMAT);
        }

        int commandLength = COMMAND.length();
        String taskInformation = userCommand.substring(commandLength).trim();
        String descriptor1 = "/from";
        String descriptor2 = "/to";
        boolean hasNoDescriptor1 = !taskInformation.contains(descriptor1);
        boolean hasNoDescriptor2 = !taskInformation.contains(descriptor2);
        boolean hasNoDate_from = taskInformation.trim().endsWith(descriptor1);
        boolean hasNoDate_to = taskInformation.trim().endsWith(descriptor2);

        if (hasNoDescriptor1 || hasNoDescriptor2) {
            throw new JamesException("Task description missing descriptor: "
                    + descriptor1 + " "
                    + descriptor2 + " \n"
                    + MESSAGE_FORMAT);
        }

        if (hasNoDate_from || hasNoDate_to) {
            throw new JamesException("Task description missing date\n"
                    + MESSAGE_FORMAT);
        }

        int startIndexEvent = userCommand.indexOf(descriptor1 + " ");
        int endIndexEvent = userCommand.indexOf(descriptor2 + " ");
        int userCmdLenEvent = userCommand.length();
        String description = userCommand.substring(commandLength, startIndexEvent).trim();

        boolean hasNoDescription = description.isBlank();


        if (hasNoDescription) {
            throw new JamesException("your task description is empty\n"
                    + MESSAGE_FORMAT);
        }
        String startEvent = userCommand.substring(startIndexEvent + 5, endIndexEvent).trim();
        String endEvent = userCommand.substring(endIndexEvent + 3, userCmdLenEvent).trim();
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
