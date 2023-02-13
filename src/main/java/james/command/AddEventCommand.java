package james.command;

import james.exception.JamesException;
import james.task.Event;
import james.task.TaskList;
import james.jamesbot.Storage;
import james.jamesbot.Ui;

/**
 * Adds a task of type Event to the task list.
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": adds a task of type event.\n"
            + "(e.g event project meeting \n"
            + "/from 25/03/2000 1800 /to 25/03/2000 1900)";

    public static final String MESSAGE_DETAILED_USAGE = "add an event task using the following format:"
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
        boolean isTaskDescriptionEmpty = userCommand.toLowerCase().replaceFirst(COMMAND_WORD, "").isBlank();
        if (isTaskDescriptionEmpty) {
            throw new JamesException("Task description is empty! \n"
                    + MESSAGE_DETAILED_USAGE);
        }

        int commandLength = COMMAND_WORD.length();
        String taskInformation = userCommand.substring(commandLength).trim();
        String descriptor_1 = "/from";
        String descriptor_2 = "/to";
        boolean hasNoDescriptor_1 = !taskInformation.contains(descriptor_1);
        boolean hasNoDescriptor_2 = !taskInformation.contains(descriptor_2);
        boolean hasNoDate_from = taskInformation.trim().endsWith(descriptor_1);
        boolean hasNoDate_to = taskInformation.trim().endsWith(descriptor_2);

        if (hasNoDescriptor_1 || hasNoDescriptor_2) {
            throw new JamesException("Task description missing descriptor: "
                    + descriptor_1 + " "
                    + descriptor_2 + " \n"
                    + MESSAGE_DETAILED_USAGE);
        }

        if (hasNoDate_from || hasNoDate_to) {
            throw new JamesException("Task description missing date\n"
                    + MESSAGE_DETAILED_USAGE);
        }

        int startIndexEvent = userCommand.indexOf(descriptor_1 + " ");
        int endIndexEvent = userCommand.indexOf(descriptor_2 + " ");
        int userCmdLenEvent = userCommand.length();
        String description = userCommand.substring(commandLength, startIndexEvent).trim();

        boolean hasNoDescription = description.isBlank();


        if (hasNoDescription) {
            throw new JamesException("your task description is empty\n"
                    + MESSAGE_DETAILED_USAGE);
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
