package duke;

import java.time.LocalDate;

/**
 * This class contains the Commands for Duke.
 */
public class Commands {

    /**
     * Executes the list command.
     *
     * @param taskList TaskList for Duke.
     * @return String to display.
     */
    public static String executeListCommand(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "You currently have no task.";
        }

        StringBuilder outputString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getArraySize(); i++) {
            Task currentTask = taskList.getTask(i);
            int taskIndex = i + 1;
            String currentString = taskIndex + ". " + currentTask + "\n";
            outputString.append(currentString);
        }
        return outputString.toString();
    }

    /**
     * Executes the find command.
     *
     * @param taskList TaskList for Duke.
     * @return String to display.
     */
    public static String executeFindCommand(String searchTerm, TaskList taskList) {
        if (taskList.isEmpty()) {
            return "You currently have no task.";
        }

        StringBuilder outputString = new StringBuilder("Here are the matching tasks in your list:\n");

        int countLabel = 1;
        for (int i = 0; i < taskList.getArraySize(); i++) {
            String currentTaskDescription = taskList.getTask(i).getDescription();
            if (currentTaskDescription.contains(searchTerm)) {
                String currentString = countLabel + ". " + taskList.getTask(i) + "\n";
                outputString.append(currentString);
                countLabel++;
            }
        }

        if (countLabel == 1) {
            // there is no task with the keyword.
            return "Here are no matching task in your list.\n";
        }

        return outputString.toString();
    }
    /**
     * Executes the mark command.
     *
     * @param input Input String.
     * @param taskList TaskList for Duke.
     * @param storage Storage for Duke.
     * @return String to display.
     * @throws DukeException If there is no such task.
     */
    public static String executeTagCommand(
            String input, TaskList taskList, Storage storage)
            throws DukeException {

        int indexToTag = Integer.parseInt(input.substring(0, 1)) - 1;
        String tagToAdd = input.substring(1);
        if (indexToTag < taskList.getArraySize()) {
            Task toTag = taskList.getTask(indexToTag);
            toTag.addTag(tagToAdd);
            storage.saveTaskListToStorage(taskList);
            return "Nice! I've tagged this task:\n" + toTag;
        } else {
            throw new DukeException("Invalid, there is no such task");
        }
    }
    /**
     * Executes the mark command.
     *
     * @param input Input String.
     * @param taskList TaskList for Duke.
     * @param storage Storage for Duke.
     * @return String to display.
     * @throws DukeException If there is no such task.
     */
    public static String executeMarkCommand(
            String input, TaskList taskList, Storage storage)
            throws DukeException {
        int indexToMark = Integer.parseInt(input) - 1;
        if (indexToMark < taskList.getArraySize()) {
            Task toMark = taskList.getTask(indexToMark);
            toMark.markAsDone();
            storage.saveTaskListToStorage(taskList);
            assert toMark.isDone() : "Marking of the task failed.";
            return "Nice! I've marked this task as done:\n" + toMark;
        } else {
            throw new DukeException("Invalid, there is no such task");
        }
    }

    /**
     * Executes the unmark command.
     *
     * @param input Input String.
     * @param taskList TaskList for Duke.
     * @param storage Storage for Duke.
     * @return String to display.
     * @throws DukeException If there is no such task
     */
    public static String executeUnmarkCommand(String input, TaskList taskList, Storage storage)
            throws DukeException {
        int indexToUnmark = Integer.parseInt(input) - 1;

        if (indexToUnmark >= taskList.getArraySize()) {
            throw new DukeException("Invalid, there is no such task");
        }

        Task toUnmark = taskList.getTask(indexToUnmark);
        toUnmark.markAsUndone();
        storage.saveTaskListToStorage(taskList);
        assert toUnmark.isDone() : "Un-marking of the task failed.";
        return "OK, I've marked this task as not done yet:\n" + toUnmark;
    }

    /**
     * Executes the delete command.
     *
     * @param input Input String.
     * @param textUi TextUi for Duke.
     * @param taskList TaskList for Duke.
     * @param storage Storage for Duke.
     * @return String to display.
     * @throws DukeException If there is no such task.
     */
    public static String executeDeleteCommand(String input, TextUi textUi, TaskList taskList, Storage storage)
            throws DukeException {
        int indexToDelete = Integer.parseInt(input) - 1;

        if (indexToDelete >= taskList.getArraySize()) {
            throw new DukeException("Invalid, there is no such task");
        }

        taskList.removeTask(indexToDelete);
        storage.saveTaskListToStorage(taskList);
        return textUi.getTaskRemovedMessage(taskList.getTask(indexToDelete), taskList.getArraySize() - 1);

    }

    /**
     * Executes the todo command.
     *
     * @param input Input String.
     * @param textUi TextUi for Duke.
     * @param taskList TaskList for Duke.
     * @param storage Storage for Duke.
     * @return String to display.
     * @throws DukeException If the given string is empty.
     */
    public static String executeToDoCommand(String input, TextUi textUi, TaskList taskList, Storage storage)
            throws DukeException {
        String processedString = Parser.removeWhiteSpaces(input);
        if (processedString.equals("todo")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        Task newTask = new ToDo(input);
        assert newTask != null : "Something went wrong when creating a new todo task.";

        taskList.addTask(newTask);
        storage.saveTaskListToStorage(taskList);
        return textUi.getTaskAddedMessage(newTask, taskList.getArraySize());
    }

    /**
     * Executes the deadline command.
     *
     * @param input Input String.
     * @param textUi TextUi for Duke.
     * @param taskList TaskList for Duke.
     * @param storage Storage for Duke.
     * @return String to display.
     * @throws DukeException If the given string is empty.
     */
    public static String executeDeadlineCommand(String input, TextUi textUi, TaskList taskList, Storage storage)
            throws DukeException {
        String processedString = Parser.removeWhiteSpaces(input);
        if (processedString.equals("deadline")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String[] str = input.split("/");
        Task newTask = new Deadline(str[0].substring(0, str[0].length() - 1),
                LocalDate.parse(str[1].substring(3)));
        assert newTask != null : "Something went wrong when creating a new deadline task.";

        taskList.addTask(newTask);
        storage.saveTaskListToStorage(taskList);
        return textUi.getTaskAddedMessage(newTask, taskList.getArraySize());
    }

    /**
     * Executes the event command.
     *
     * @param input Input String.
     * @param textUi TextUi for Duke.
     * @param taskList TaskList for Duke.
     * @param storage Storage for Duke.
     * @return String to display.
     * @throws DukeException If the given string is empty.
     */
    public static String executeEventCommand(String input, TextUi textUi, TaskList taskList, Storage storage)
            throws DukeException {
        String processedString = Parser.removeWhiteSpaces(input);
        if (processedString.equals("event")) {
            throw new DukeException("The description of a event cannot be empty.");
        }

        String[] str = input.split("/");
        Task newTask = new Event(str[0].substring(0, str[0].length() - 1),
                LocalDate.parse(str[1].substring(5, str[1].length() - 1)),
                LocalDate.parse(str[2].substring(3)));
        assert newTask != null : "Something went wrong when creating a new Event task.";

        taskList.addTask(newTask);
        storage.saveTaskListToStorage(taskList);
        return textUi.getTaskAddedMessage(newTask, taskList.getArraySize());
    }
}
