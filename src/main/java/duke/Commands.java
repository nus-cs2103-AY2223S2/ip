package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.NoDescriptionException;
import duke.exceptions.TaskNotFoundException;
import duke.exceptions.WrongDateFormatException;

/**
 * This class contains the Commands for Duke to execute.
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
     * @param searchTerm The search term entered by user.
     * @param taskList TaskList for Duke.
     * @return String to display.
     * @throws NoDescriptionException If there is no description given.
     */
    public static String executeFindCommand(String searchTerm, TaskList taskList)
            throws NoDescriptionException {
        String processedString = Parser.removeWhiteSpaces(searchTerm);

        if (processedString.equals("")) {
            throw new NoDescriptionException("find");
        }

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
     * @throws TaskNotFoundException If there is no task corresponding to the index on taskList.
     * @throws NoDescriptionException If there is no description given.
     */
    public static String executeTagCommand(String input, TaskList taskList, Storage storage)
            throws TaskNotFoundException, NoDescriptionException {
        String processedString = Parser.removeWhiteSpaces(input);

        if (processedString.equals("")) {
            throw new NoDescriptionException("tag");
        }

        int indexToTag = Integer.parseInt(input.substring(0, 1)) - 1;
        String tagToAdd = input.substring(1);

        if (indexToTag >= taskList.getArraySize() || indexToTag < 0) {
            throw new TaskNotFoundException();
        }

        Task toTag = taskList.getTask(indexToTag);
        toTag.addTag(tagToAdd);
        storage.saveTaskListToStorage(taskList);
        return "Nice! I've tagged this task:\n" + toTag;

    }

    /**
     * Executes the mark command.
     *
     * @param input Input String.
     * @param taskList TaskList for Duke.
     * @param storage Storage for Duke.
     * @return String to display.
     * @throws TaskNotFoundException If there is no task corresponding to the index on taskList.
     * @throws NoDescriptionException If there is no description given.
     */
    public static String executeMarkCommand(String input, TaskList taskList, Storage storage)
            throws TaskNotFoundException, NoDescriptionException {
        String processedString = Parser.removeWhiteSpaces(input);

        if (processedString.equals("")) {
            throw new NoDescriptionException("mark");
        }

        int indexToMark = Integer.parseInt(input) - 1;

        if (indexToMark >= taskList.getArraySize() || indexToMark < 0) {
            throw new TaskNotFoundException();
        }

        Task toMark = taskList.getTask(indexToMark);
        toMark.markAsDone();
        storage.saveTaskListToStorage(taskList);
        assert toMark.isDone() : "Marking of the task failed.";
        return "Nice! I've marked this task as done:\n" + toMark;
    }

    /**
     * Executes the unmark command.
     *
     * @param input Input String.
     * @param taskList TaskList for Duke.
     * @param storage Storage for Duke.
     * @return String to display.
     * @throws TaskNotFoundException If there is no task corresponding to the index on taskList.
     * @throws NoDescriptionException If there is no description given.
     */
    public static String executeUnmarkCommand(String input, TaskList taskList, Storage storage)
            throws TaskNotFoundException , NoDescriptionException {

        String processedString = Parser.removeWhiteSpaces(input);
        if (processedString.equals("")) {
            throw new NoDescriptionException("unmark");
        }

        int indexToUnmark = Integer.parseInt(input) - 1;

        if (indexToUnmark >= taskList.getArraySize() || indexToUnmark < 0) {
            throw new TaskNotFoundException();
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
     * @throws TaskNotFoundException If there is no task corresponding to the index on taskList.
     * @throws NoDescriptionException If there is no description given.
     */
    public static String executeDeleteCommand(String input, TextUi textUi, TaskList taskList, Storage storage)
            throws TaskNotFoundException, NoDescriptionException {
        String processedString = Parser.removeWhiteSpaces(input);
        if (processedString.equals("")) {
            throw new NoDescriptionException("delete");
        }

        int indexToDelete = Integer.parseInt(input) - 1;
        if (indexToDelete >= taskList.getArraySize() || indexToDelete < 0) {
            throw new TaskNotFoundException();
        }

        taskList.removeTask(indexToDelete);
        storage.saveTaskListToStorage(taskList);
        return textUi.getTaskRemovedMessage(taskList.getTask(indexToDelete),
                taskList.getArraySize() - 1);
    }

    /**
     * Executes the todo command.
     *
     * @param input Input String.
     * @param textUi TextUi for Duke.
     * @param taskList TaskList for Duke.
     * @param storage Storage for Duke.
     * @return String to display.
     * @throws NoDescriptionException If there is no description given.
     */
    public static String executeToDoCommand(String input, TextUi textUi, TaskList taskList, Storage storage)
            throws NoDescriptionException {
        String processedString = Parser.removeWhiteSpaces(input);

        if (processedString.equals("")) {
            throw new NoDescriptionException("todo");
        }

        Task newTask = new ToDo(input);

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
     * @throws NoDescriptionException If there is no description given.
     * @throws WrongDateFormatException If the date is not of the correct format.
     */
    public static String executeDeadlineCommand(String input, TextUi textUi, TaskList taskList, Storage storage)
            throws NoDescriptionException, WrongDateFormatException {
        String processedString = Parser.removeWhiteSpaces(input);
        if (processedString.equals("")) {
            throw new NoDescriptionException("deadline");
        }

        String[] str = input.split("/");
        try {
            Task newTask = new Deadline(str[0].substring(0, str[0].length() - 1),
                    stringToDate(str[1].substring(3)));
            taskList.addTask(newTask);
            storage.saveTaskListToStorage(taskList);
            return textUi.getTaskAddedMessage(newTask, taskList.getArraySize());
        } catch (WrongDateFormatException e) {
            throw new WrongDateFormatException();
        }
    }

    /**
     * Executes the event command.
     *
     * @param input Input String.
     * @param textUi TextUi for Duke.
     * @param taskList TaskList for Duke.
     * @param storage Storage for Duke.
     * @return String to display.
     * @throws NoDescriptionException If there is no description given.
     * @throws WrongDateFormatException If the date is not of the correct format.
     */
    public static String executeEventCommand(String input, TextUi textUi, TaskList taskList, Storage storage)
            throws NoDescriptionException, WrongDateFormatException {

        String processedString = Parser.removeWhiteSpaces(input);
        if (processedString.equals("")) {
            throw new NoDescriptionException("event");
        }

        String[] str = input.split("/");
        try {
            Task newTask = new Event(str[0].substring(0, str[0].length() - 1),
                    stringToDate(str[1].substring(5, str[1].length() - 1)),
                    stringToDate(str[2].substring(3)));
            taskList.addTask(newTask);
            storage.saveTaskListToStorage(taskList);
            return textUi.getTaskAddedMessage(newTask, taskList.getArraySize());
        } catch (WrongDateFormatException e) {
            throw new WrongDateFormatException();
        }
    }

    /**
     * Parses the given String to a LocalDate.
     * @param s String representing the date.
     * @return LocalDate corresponding to the given string.
     * @throws WrongDateFormatException If the given string does not follow the correct format.
     */
    public static LocalDate stringToDate(String s) throws WrongDateFormatException {
        try {
            return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new WrongDateFormatException();
        }
    }
}
