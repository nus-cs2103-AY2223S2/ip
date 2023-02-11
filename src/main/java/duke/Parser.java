package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
     */
    public String parse(String input) throws WrongTask, EmptyDescription, OutOfBounds {
        String[] arrNext = input.split(" ", 2);
        String next = arrNext[0];
        checkWrongTask(next);
        checkEmptyDescription(arrNext);

        Task inputTask;
        switch (next) {
        case "bye": {
            return ui.exit();
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
        default:
            throw new WrongTask(" I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Task newTodo(Storage storage, TaskList taskList, String description) {
        Task newTodo = new Todo(description);
        taskList.addTask(newTodo);
        storage.appendToFile(newTodo);
        return newTodo;
    }

    private static Task newDeadline(Storage storage, TaskList taskList, String description) {
        String[] split = description.split("/by ");
        LocalDate d1 = LocalDate.parse(split[1]);
        Task newDeadline = new Deadline(split[0], d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        taskList.addTask(newDeadline);
        storage.appendToFile(newDeadline);
        return newDeadline;
    }

    private static Task newEvent(Storage storage, TaskList taskList, String description) {
        String[] split = description.split("/");
        Task newEvent = new Event(split[0], split[1].substring(5), split[2].substring(3));
        taskList.addTask(newEvent);
        storage.appendToFile(newEvent);
        return newEvent;
    }

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

    private static Task markTask(Storage storage, TaskList taskList, String taskID) {
        int number = Integer.parseInt(taskID) - 1;
        Task toMarkDone = taskList.getTask(number);

        toMarkDone.markAsDone();
        storage.updateFile(taskList);
        return toMarkDone;
    }

    private static Task unmarkTask(Storage storage, TaskList taskList, String taskID) {
        int number = Integer.parseInt(taskID) - 1;
        Task toUnmarkDone = taskList.getTask(number);

        toUnmarkDone.unMarkAsDone();
        storage.updateFile(taskList);
        return toUnmarkDone;
    }

    private static Task deleteTask(Storage storage, TaskList taskList, String taskID) {
        int number = Integer.parseInt(taskID) - 1;
        Task taskToDelete = taskList.getTask(number);
        taskList.deleteTask(taskToDelete);
        storage.updateFile(taskList);
        return taskToDelete;
    }


    /**
     * Checks if a task has an empty description.
     *
     * @param checkString User input to be checked.
     * @return True or false depending on whether description of task is empty.
     * @throws EmptyDescription If the description of task is empty.
     */
    public static boolean checkEmptyDescription(String[] checkString) throws EmptyDescription {
        if (checkString.length == 1 && !Objects.equals(checkString[0], "list") && !Objects.equals(checkString[0], "bye")) {
            throw new EmptyDescription(" The description of " + checkString[0] + " cannot be empty.");
        }
        return true;
    }

    public static void checkOutOfBounds(String[] checkString, TaskList taskList) throws OutOfBounds {
        int number = Integer.parseInt(checkString[1]);
        if (number < 1 || number > taskList.getNumberOfTasks()) {
            throw new OutOfBounds(" The index given for " + checkString[0] + " is out of bound.");
        }
    }

    public static void checkWrongTask(String keyword) throws WrongTask {
        List<String> keywords = Arrays.asList("bye", "todo", "deadline", "event", "mark", "unmark", "list", "delete", "find");
        boolean isKeyword = keywords.contains(keyword);
        if (!isKeyword) {
            throw new WrongTask(" I'm sorry, but I don't know what that means :-(");
        }
    }
}

