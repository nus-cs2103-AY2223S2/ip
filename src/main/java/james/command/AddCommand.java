package james.command;

import james.exception.JamesException;

import james.jamesbot.Storage;
import james.jamesbot.Ui;

import james.task.Deadline;
import james.task.Event;
import james.task.Task;
import james.task.TaskList;
import james.task.ToDo;


/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    /** The description of the task. */
    private String description;

    /** The type of task to be added. */
    private String taskType;

    /** The user command parsed into AddCommand. */
    private String userCommand;

    /**
     * Constructor for an AddCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public AddCommand(String userCommand) {
        this.userCommand = userCommand;
        String[] taskData = userCommand.split(" ", 2);
        taskType = taskData[0];
        description = taskData[1];
    }

    /**
     * Executes the AddCommand which adds a task of taskType into a stored task list.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out JamesBot response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws JamesException If task description is empty;
     *                      If task does not contain keyword;
     *                      If date is empty for deadline and event tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamesException {
        if (userCommand.replaceFirst(taskType, "").isBlank()) {
            throw new JamesException("your task description is empty");
        }

        String response = "oops looks like something went wrong while adding your task";

        switch (taskType) {
        case "todo":
            ToDo todo = new ToDo(description);
            tasks.add(todo);
            storage.save(tasks.taskListToStoreString());
            response = ui.addTask(todo, tasks.size());
            break;
        case "deadline":
            String descriptor = "/by";

            if (!description.contains(descriptor)) {
                throw new JamesException("please make sure your task contains the keyword "
                        + descriptor);
            }

            if (description.trim().endsWith(descriptor)) {
                throw new JamesException("Looks like the date is missing~"
                        + "\nplease enter a date after the " + descriptor
                        + " in this format:" + "d/MM/yyyy HHmm");
            }

            int startIndexDeadline = userCommand.indexOf(descriptor + " ");
            int userCmdLen = userCommand.length();
            description = userCommand.substring(taskType.length(), startIndexDeadline).trim();

            if (description.isBlank()) {
                throw new JamesException("your task description is empty TT");
            }

            String startDeadline = userCommand.substring(startIndexDeadline + 3, userCmdLen).trim();


            Task taskDeadline = new Deadline(description, startDeadline);
            tasks.add(taskDeadline);
            storage.save(tasks.taskListToStoreString());
            response = ui.addTask(taskDeadline, tasks.size());
            break;
        case "event":
            String descriptor_1 = "/from";
            String descriptor_2 = "/to";

            if (!description.contains(descriptor_1) || !description.contains(descriptor_2)) {
                throw new JamesException("please make sure your task contains the keyword "
                        + descriptor_1 + " and" + descriptor_2);
            }

            if (description.trim().endsWith(descriptor_1) || description.trim().endsWith(descriptor_2)) {
                throw new JamesException("Looks like the date is missing"
                        + "\nplease enter a date after the " + descriptor_1 + "and"
                        + descriptor_2 + " in this format: d/MM/yyyy HHmm");
            }

            int startIndexEvent = userCommand.indexOf(descriptor_1 + " ");
            int endIndexEvent = userCommand.indexOf(descriptor_2 + " ");
            int userCmdLenEvent = userCommand.length();
            description = userCommand.substring(taskType.length(), startIndexEvent).trim();

            if (description.isBlank()) {
                throw new JamesException("your task description is empty");
            }

            String startEvent = userCommand.substring(startIndexEvent + 5, endIndexEvent).trim();
            String endEvent = userCommand.substring(endIndexEvent + 3, userCmdLenEvent).trim();

            Task taskEvent = new Event(description, startEvent, endEvent);
            tasks.add(taskEvent);
            storage.save(tasks.taskListToStoreString());
            response = ui.addTask(taskEvent, tasks.size());
            break;
        default:
            assert false: "Should not reach here for AddCommand"; // Will not reach here

        }
        return response;
    }
    /**
     * Returns whether AddCommand exits the program.
     *
     * @return false as AddCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
