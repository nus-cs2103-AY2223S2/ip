package duke;

import java.time.LocalDate;

public class Commands {
    public static void executeListCommand(TextUi TextUi, TaskList taskList) {
        TextUi.printLine();
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
        TextUi.printLine();
    }

    public static void executeMarkCommand(String input, TextUi TextUi
            , TaskList taskList, Storage storage) throws DukeException {
        int indexToMark = Integer.parseInt(input) - 1;
        if (indexToMark < taskList.getArraySize()) {
            Task toMark = taskList.getTask(indexToMark);
            toMark.markAsDone();
            storage.saveTaskListToStorage(taskList);
            TextUi.getCustomMessage("Nice! I've marked this task as done:\n" + toMark);
        } else {
            throw new DukeException("Invalid, there is no such task");
        }
    }

    public static void executeUnmarkCommand(String input, TextUi TextUi
            , TaskList taskList, Storage storage) throws DukeException {
        int indexToUnmark = Integer.parseInt(input) - 1;
        if (indexToUnmark < taskList.getArraySize()) {
            Task toUnmark = taskList.getTask(indexToUnmark);
            toUnmark.markAsUndone();
            storage.saveTaskListToStorage(taskList);
            TextUi.getCustomMessage("OK, I've marked this task as not done yet:\n" + toUnmark);
        } else {
            throw new DukeException("Invalid, there is no such task");
        }
    }

    public static void executeDeleteCommand(String input, TextUi TextUi
            , TaskList taskList, Storage storage) throws DukeException {
        int indexToDelete = Integer.parseInt(input) - 1;
        if (indexToDelete < taskList.getArraySize()) {
            TextUi.getTaskRemovedMessage(taskList.getTask(indexToDelete), taskList.getArraySize() - 1);
            taskList.removeTask(indexToDelete);
            storage.saveTaskListToStorage(taskList);
        } else {
            throw new DukeException("Invalid, there is no such task");
        }
    }

    public static void executeToDoCommand(String input, TextUi TextUi
            , TaskList taskList, Storage storage) throws DukeException {
        String check = Parser.removeWhiteSpaces(input);
        if (check.equals("todo")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task newTask = new ToDo(input);
        taskList.addTask(newTask);
        TextUi.getTaskAddedMessage(newTask, taskList.getArraySize());
        storage.saveTaskListToStorage(taskList);
    }

    public static void executeDeadlineCommand(String input, TextUi TextUi
            , TaskList taskList, Storage storage) throws DukeException {
        String check = Parser.removeWhiteSpaces(input);
        if (check.equals("deadline")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String[] str = input.split("/");
        Task newTask = new Deadline(str[0].substring(0, str[0].length() - 1)
                , LocalDate.parse(str[1].substring(3)));
        taskList.addTask(newTask);
        TextUi.getTaskAddedMessage(newTask, taskList.getArraySize());
        storage.saveTaskListToStorage(taskList);
    }

    public static void executeEventCommand(String input, TextUi TextUi
            , TaskList taskList, Storage storage) throws DukeException {
        String check = Parser.removeWhiteSpaces(input);
        if (check.equals("event")) {
            throw new DukeException("The description of a event cannot be empty.");
        }
        String[] str = input.split("/");
        Task newTask = new Event(str[0].substring(0, str[0].length() - 1)
                , LocalDate.parse(str[1].substring(5, str[1].length() - 1))
                , LocalDate.parse(str[2].substring(3)));
        taskList.addTask(newTask);
        TextUi.getTaskAddedMessage(newTask, taskList.getArraySize());
        storage.saveTaskListToStorage(taskList);
    }
}