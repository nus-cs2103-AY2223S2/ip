package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Handles user inputs for parsing.
 */
public class Parser {
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses user inputs.
     *
     * @param input User input.
     * @throws WrongTask        If the keyword is not the input.
     * @throws EmptyDescription If the description of task is empty.
     * @throws OutOfBounds      If the number is out of bound.
     */
    public String parse(String input) throws WrongTask, EmptyDescription, OutOfBounds, InvalidDeadlineDate, NoDate,
            InvalidEventFormat, InvalidUpdateEventFormat {
        String[] arrNext = input.split(" ", 2);
        String next = arrNext[0];
        checkWrongTask(next);
        checkEmptyDescription(arrNext);

        Task inputTask;
        switch (next) {
        case "bye": {
            return ui.exit();
        }
        case "help": {
            return ui.userGuide();
        }
        case "todo": {
            String after = arrNext[1];
            inputTask = newTodo(storage, taskList, after);
            return ui.showTaskOutput(inputTask, taskList.getNumberOfTasks());
        }
        case "deadline": {
            String after = arrNext[1];
            inputTask = newDeadline(storage, taskList, after);
            return ui.showTaskOutput(inputTask, taskList.getNumberOfTasks());
        }
        case "event": {
            String after = arrNext[1];
            inputTask = newEvent(storage, taskList, after);
            return ui.showTaskOutput(inputTask, taskList.getNumberOfTasks());
        }
        case "mark": {
            String after = arrNext[1];
            checkOutOfBounds(arrNext, taskList);
            inputTask = markTask(storage, taskList, after);
            return ui.showMark(inputTask);
        }
        case "list": {
            return taskList.getTaskList();
        }
        case "find": {
            String after = arrNext[1];
            return ui.printMatchingTasks(findTasks(taskList, after));
        }
        case "unmark": {
            String after = arrNext[1];
            checkOutOfBounds(arrNext, taskList);
            inputTask = unmarkTask(storage, taskList, after);
            return ui.showUnmark(inputTask);
        }
        case "delete": {
            String after = arrNext[1];
            checkOutOfBounds(arrNext, taskList);
            inputTask = deleteTask(storage, taskList, after);
            return ui.showDelete(inputTask, taskList.getNumberOfTasks());
        }
        case "update": {
            String after = arrNext[1];
            String[] splitUpdate = after.split(" ", 2);
            inputTask = updateTask(storage, taskList, splitUpdate[0], splitUpdate[1]);
            return ui.showUpdate(inputTask);
        }
        default:
            throw new WrongTask(" I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Creates new Todo task and appends it to file.
     *
     * @param storage     File to append new Todo task.
     * @param taskList    The list to add the task.
     * @param description The description of the Todo task.
     * @return The new Todo task.
     */
    private static Task newTodo(Storage storage, TaskList taskList, String description) {
        Task newTodo = new Todo(description);
        taskList.addTask(newTodo);
        storage.appendToFile(newTodo);
        return newTodo;
    }

    /**
     * Creates new Deadline task and appends it to file.
     *
     * @param storage     File to append new Deadline task.
     * @param taskList    The list to add the task.
     * @param description The description of the Deadline task.
     * @return The new Deadline task.
     */
    private static Task newDeadline(Storage storage, TaskList taskList, String description) throws
            InvalidDeadlineDate, NoDate {
        try {
            checkDeadlineDate(description);
            String[] split = description.split("/by ");
            LocalDate d1 = LocalDate.parse(split[1]);
            Task newDeadline = new Deadline(split[0], d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            taskList.addTask(newDeadline);
            storage.appendToFile(newDeadline);
            return newDeadline;
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineDate();
        }
    }

    /**
     * Creates new Event task and appends it to file.
     *
     * @param storage     File to append new Event task.
     * @param taskList    The list to add the task.
     * @param description The description of the Event task.
     * @return The new Event task.
     */
    private static Task newEvent(Storage storage, TaskList taskList, String description) throws InvalidEventFormat {
        checkEventFormat(description);
        String[] split = description.split("/");
        Task newEvent = new Event(split[0], split[1].substring(5), split[2].substring(3));
        taskList.addTask(newEvent);
        storage.appendToFile(newEvent);
        return newEvent;
    }

    /**
     * Find specific tasks.
     *
     * @param taskList    The list to retrieve the task.
     * @param description The keyword to match the tasks in the list.
     * @return The tasklist containing matching tasks.
     */
    private static TaskList findTasks(TaskList taskList, String description) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            Task task = taskList.getTask(i);
            String descriptionOfTask = task.description;
            String[] splitTaskString = descriptionOfTask.split(" ");
            for (String s : splitTaskString) {
                if (Objects.equals(s, description)) {
                    matchingTasks.addTask(task);
                }
            }
        }
        return matchingTasks;
    }

    /**
     * Marks task as done.
     *
     * @param storage  File to update after marking task.
     * @param taskList The list to retrieve the task.
     * @param taskID   The position of the task.
     * @return The marked task.
     */
    private static Task markTask(Storage storage, TaskList taskList, String taskID) {
        int number = Integer.parseInt(taskID) - 1;
        Task toMarkDone = taskList.getTask(number);

        toMarkDone.markAsDone();
        storage.updateFile(taskList);
        return toMarkDone;
    }

    /**
     * Unmarks task as done.
     *
     * @param storage  File to update after unmarking task.
     * @param taskList The list to retrieve the task.
     * @param taskID   The position of the task.
     * @return The unmarked task.
     */
    private static Task unmarkTask(Storage storage, TaskList taskList, String taskID) {
        int number = Integer.parseInt(taskID) - 1;
        Task toUnmarkDone = taskList.getTask(number);

        toUnmarkDone.unMarkAsDone();
        storage.updateFile(taskList);
        return toUnmarkDone;
    }

    /**
     * Deletes a task.
     *
     * @param storage  File to update after deletion of task.
     * @param taskList The list to retrieve the task.
     * @param taskID   The position of the task.
     * @return The deleted task.
     */
    private static Task deleteTask(Storage storage, TaskList taskList, String taskID) {
        int number = Integer.parseInt(taskID) - 1;
        Task taskToDelete = taskList.getTask(number);
        taskList.deleteTask(taskToDelete);
        storage.updateFile(taskList);
        return taskToDelete;
    }

    /**
     * Update details of the task.
     *
     * @param storage  File to store the updated input.
     * @param taskList The list to retrieve the task.
     * @param taskID   The position of the task.
     * @param newDesc  The new description of the task.
     * @return The updated task.
     */
    private static Task updateTask(Storage storage, TaskList taskList, String taskID, String newDesc) throws
            InvalidDeadlineDate, NoDate, InvalidUpdateEventFormat {
        int number = Integer.parseInt(taskID) - 1;
        Task taskToUpdate = taskList.getTask(number);
        if (taskToUpdate instanceof Event) {
            checkUpdateEventFormat(newDesc);
            String[] newDescArr = newDesc.split("/");
            ((Event) taskToUpdate).setFrom(newDescArr[1].substring(5));
            ((Event) taskToUpdate).setTo(newDescArr[2].substring(3));
        }
        if (taskToUpdate instanceof Deadline) {
            checkDeadlineDate(newDesc);
            try {
                LocalDate newDate = LocalDate.parse(newDesc.substring(4));
                ((Deadline) taskToUpdate).setBy(newDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            } catch (DateTimeParseException e) {
                throw new InvalidDeadlineDate();
            }
        }
        storage.updateFile(taskList);
        return taskToUpdate;
    }

    /**
     * Checks if a task has an empty description.
     *
     * @param checkString User input to be checked.
     * @return True or false depending on whether description of task is empty.
     * @throws EmptyDescription If the description of task is empty.
     */
    public static boolean checkEmptyDescription(String[] checkString) throws EmptyDescription {
        if (checkString.length == 1 && !Objects.equals(checkString[0], "list")
                && !Objects.equals(checkString[0], "bye") && !Objects.equals(checkString[0], "help")) {
            throw new EmptyDescription(" The description of " + checkString[0] + " cannot be empty.");
        }
        return true;
    }

    /**
     * Checks if number is out of bounds.
     *
     * @param checkString User input to be checked.
     * @param taskList    The list to be checked.
     * @throws OutOfBounds If the number is out of bound.
     */
    public static void checkOutOfBounds(String[] checkString, TaskList taskList) throws OutOfBounds {
        int number = Integer.parseInt(checkString[1]);
        if (number < 1 || number > taskList.getNumberOfTasks()) {
            throw new OutOfBounds(" The index given for " + checkString[0] + " is out of bound.");
        }
    }

    /**
     * Checks if wrong task is inputted.
     *
     * @param keyword User input to be checked.
     * @throws WrongTask If the keyword is not the input.
     */
    public static void checkWrongTask(String keyword) throws WrongTask {
        List<String> keywords = Arrays.asList("bye", "todo", "deadline", "event", "mark", "unmark", "list",
                "delete", "find", "update", "help");
        boolean isKeyword = keywords.contains(keyword);
        if (!isKeyword) {
            throw new WrongTask(" I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Checks if there is a date for Deadline tasks.
     *
     * @param taskDesc Description of the Deadline task.
     * @throws NoDate If the Deadline task has no date.
     */
    public static void checkDeadlineDate(String taskDesc) throws NoDate {
        String checkBy = "/by";
        boolean hasDate = taskDesc.contains(checkBy);
        if (!hasDate) {
            throw new NoDate();
        }
    }

    /**
     * Checks if Event task has start and end time.
     *
     * @param taskDesc Description of the Event task.
     * @throws InvalidEventFormat If the Event task has no start or end time.
     */
    public static void checkEventFormat(String taskDesc) throws InvalidEventFormat {
        String checkFrom = "/from";
        String checkTo = "/to";
        boolean hasStartAndEnd = taskDesc.contains(checkFrom) && taskDesc.contains(checkTo);
        if (!hasStartAndEnd) {
            throw new InvalidEventFormat();
        }
    }

    /**
     * Checks if updating Event tasks in correct format.
     *
     * @param newDesc Description of the update event.
     * @throws InvalidUpdateEventFormat If Event task is not updated in correct format.
     */
    public static void checkUpdateEventFormat(String newDesc) throws InvalidUpdateEventFormat {
        String checkFrom = "/from";
        String checkTo = "/to";
        boolean hasStartAndEnd = newDesc.contains(checkFrom) && newDesc.contains(checkTo);
        if (!hasStartAndEnd) {
            throw new InvalidUpdateEventFormat();
        }
    }
}

