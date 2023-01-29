package duke;

import java.time.LocalDate;

/**
 * This class contains the Commands for Duke.
 */
public class Commands {

    /**
     * Executes the list command.
     * @param textUi   TextUi for Duke.
     * @param taskList TaskList for Duke.
     */
    public static void executeListCommand(TextUi textUi, TaskList taskList) {
        textUi.printLine();

        if (taskList.isEmpty()) {
            System.out.println("You currently have no task.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getArraySize(); i++) {
                Task currTask = taskList.getTask(i);
                int taskIndex = i + 1;
                System.out.println(taskIndex + ". " + currTask);
            }
        }
        textUi.printLine();
    }

    /**
     * Executes the find command.
     *
     * @param textUi   TextUi for Duke.
     * @param taskList TaskList for Duke.
     */
    public static void executeFindCommand(String toFind, TextUi textUi, TaskList taskList) {
        textUi.printLine();
        if (taskList.isEmpty()) {
            System.out.println("You currently have no task.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int count = 1;
            for (int i = 0; i < taskList.getArraySize(); i++) {
                String currentTaskDescription = taskList.getTask(i).getDescription();
                if (currentTaskDescription.contains(toFind)) {
                    System.out.println(count + ". " + taskList.getTask(i));
                    count++;
                }
            }
            if (count == 1) {
                System.out.println("Here are no matching task in your list.");
            }
        }
        textUi.printLine();
    }

    /**
     * Executes the mark command.
     * @param input    input String.
     * @param textUi   TextUi for Duke.
     * @param taskList TaskList for Duke.
     * @param storage  Storage for Duke.
     * @throws DukeException If there is no such task.
     */
    public static void executeMarkCommand(
            String input, TextUi textUi, TaskList taskList, Storage storage)
            throws DukeException {
        int indexToMark = Integer.parseInt(input) - 1;
        if (indexToMark < taskList.getArraySize()) {
            Task toMark = taskList.getTask(indexToMark);
            toMark.markAsDone();
            storage.saveTaskListToStorage(taskList);
            textUi.getCustomMessage("Nice! I've marked this task as done:\n" + toMark);
        } else {
            throw new DukeException("Invalid, there is no such task");
        }
    }

    /**
     * Executes the unmark command.
     * @param input    input String.
     * @param textUi   TextUi for Duke.
     * @param taskList TaskList for Duke.
     * @param storage  Storage for Duke.
     * @throws DukeException
     */
    public static void executeUnmarkCommand(
            String input, TextUi textUi, TaskList taskList, Storage storage)
            throws DukeException {
        int indexToUnmark = Integer.parseInt(input) - 1;
        if (indexToUnmark < taskList.getArraySize()) {
            Task toUnmark = taskList.getTask(indexToUnmark);
            toUnmark.markAsUndone();
            storage.saveTaskListToStorage(taskList);
            textUi.getCustomMessage("OK, I've marked this task as not done yet:\n" + toUnmark);
        } else {
            throw new DukeException("Invalid, there is no such task");
        }
    }

    /**
     * Executes the delete command.
     * @param input    input String.
     * @param textUi   TextUi for Duke.
     * @param taskList TaskList for Duke.
     * @param storage  Storage for Duke.
     * @throws DukeException
     */
    public static void executeDeleteCommand(String input, TextUi textUi, TaskList taskList, Storage storage)
            throws DukeException {
        int indexToDelete = Integer.parseInt(input) - 1;
        if (indexToDelete < taskList.getArraySize()) {
            textUi.getTaskRemovedMessage(taskList.getTask(indexToDelete), taskList.getArraySize() - 1);
            taskList.removeTask(indexToDelete);
            storage.saveTaskListToStorage(taskList);
        } else {
            throw new DukeException("Invalid, there is no such task");
        }
    }

    /**
     * Executes the todo command.
     * @param input    input String.
     * @param textUi   TextUi for Duke.
     * @param taskList TaskList for Duke.
     * @param storage  Storage for Duke.
     * @throws DukeException If the given string is empty.
     */
    public static void executeToDoCommand(String input, TextUi textUi, TaskList taskList, Storage storage)
            throws DukeException {
        String check = Parser.removeWhiteSpaces(input);
        if (check.equals("todo")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task newTask = new ToDo(input);
        taskList.addTask(newTask);
        textUi.getTaskAddedMessage(newTask, taskList.getArraySize());
        storage.saveTaskListToStorage(taskList);
    }

    /**
     * Executes the deadline command.
     * @param input    input String.
     * @param textUi   TextUi for Duke.
     * @param taskList TaskList for Duke.
     * @param storage  Storage for Duke.
     * @throws DukeException If the given string is empty.
     */
    public static void executeDeadlineCommand(String input, TextUi textUi, TaskList taskList, Storage storage)
            throws DukeException {
        String check = Parser.removeWhiteSpaces(input);
        if (check.equals("deadline")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] str = input.split("/");
        Task newTask = new Deadline(str[0].substring(0, str[0].length() - 1),
                LocalDate.parse(str[1].substring(3)));
        taskList.addTask(newTask);
        textUi.getTaskAddedMessage(newTask, taskList.getArraySize());
        storage.saveTaskListToStorage(taskList);
    }

    /**
     * Executes the event command.
     * @param input    input String.
     * @param textUi   TextUi for Duke.
     * @param taskList TaskList for Duke.
     * @param storage  Storage for Duke.
     * @throws DukeException If the given string is empty.
     */
    public static void executeEventCommand(String input, TextUi textUi, TaskList taskList, Storage storage)
            throws DukeException {
        String check = Parser.removeWhiteSpaces(input);
        if (check.equals("event")) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        String[] str = input.split("/");
        Task newTask = new Event(str[0].substring(0, str[0].length() - 1),
                LocalDate.parse(str[1].substring(5, str[1].length() - 1)),
                LocalDate.parse(str[2].substring(3)));
        taskList.addTask(newTask);
        textUi.getTaskAddedMessage(newTask, taskList.getArraySize());
        storage.saveTaskListToStorage(taskList);
    }
}