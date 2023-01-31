package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
     * @param input User input.
     */
    public void parse(String input) {
        String[] arrNext = input.split(" ", 2);
        String next = arrNext[0];
        if (Objects.equals(next, "bye")) {
            ui.exit();
        }

        try {
            if (arrNext.length <= 1) {
                if (Objects.equals(next, "todo") || Objects.equals(next, "deadline") ||
                        Objects.equals(next, "event") || Objects.equals(next, "mark") ||
                        Objects.equals(next, "unmark") || Objects.equals(next, "delete")) {
                    if (!Objects.equals(next, "list") && !Objects.equals(next, "bye")) {
                        throw new EmptyDescription(" The description of " + next + " cannot be empty.");
                    }
                }
                if ((!Objects.equals(next, "todo") && !Objects.equals(next, "deadline") &&
                        !Objects.equals(next, "event"))) {
                    if (!Objects.equals(next, "list") && !Objects.equals(next, "mark") &&
                            !Objects.equals(next, "unmark") && !Objects.equals(next, "delete") &&
                            !Objects.equals(next, "bye")) {
                        throw new WrongTask(" I'm sorry, but I don't know what that means :-(");
                    }
                }
            }
        } catch (EmptyDescription |
                WrongTask e2) {
            System.out.println(e2);
            String nextLine = ui.userInput();
            arrNext = nextLine.split(" ", 2);
            next = arrNext[0];
        } finally {
            if (Objects.equals(next, "bye")) {
                ui.exit();
            }
        }
        String after = null;
        try {
            after = arrNext[1];
        } catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println("I will show your saved tasks.");
        }

        Task inputTask;
        switch (next) {
        case "todo": {
            inputTask = new Todo(after);
            taskList.addTask(inputTask);
            System.out.println("Got it. I've added this task:\n" + "  " + inputTask);
            System.out.println("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
            storage.appendToFile(inputTask);
            break;
        }
        case "deadline": {
            String[] split = after.split("/by ");
            LocalDate d1 = LocalDate.parse(split[1]);
            inputTask = new Deadline(split[0], d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            taskList.addTask(inputTask);
            System.out.println("Got it. I've added this task:\n" + "  " + inputTask);
            System.out.println("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
            storage.appendToFile(inputTask);
            break;
        }
        case "event": {
            String[] split = after.split("/");
            inputTask = new Event(split[0], split[1].substring(5), split[2].substring(3));
            taskList.addTask(inputTask);
            System.out.println("Got it. I've added this task:\n" + "  " + inputTask);
            System.out.println("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
            storage.appendToFile(inputTask);
            break;
        }
        case "mark": {
            int number = Integer.parseInt(after);
            try {
                if (number > taskList.getNumberOfTasks()) {
                    throw new OutOfBounds(" There is no such element :-p");
                }
            } catch (OutOfBounds e3) {
                System.out.println(e3);
                break;
            }
            Task toMarkDone = taskList.getTask(number - 1);
            toMarkDone.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + toMarkDone);
            storage.updateFile(taskList);
            break;
        }
        case "list": {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
                System.out.println(i + 1 + "." + taskList.getTask(i));
            }
            break;
        }
        case "unmark": {
            int number = Integer.parseInt(after);
            try {
                if (number > taskList.getNumberOfTasks()) {
                    throw new OutOfBounds(" There is no such element :-p");
                }
            } catch (OutOfBounds e3) {
                System.out.println(e3);
                break;
            }
            Task toUnMarkDone = taskList.getTask(number - 1);
            toUnMarkDone.unMarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(" " + toUnMarkDone);
            storage.updateFile(taskList);
            break;
        }
        case "delete": {
            int number = Integer.parseInt(after);
            try {
                if (number > taskList.getNumberOfTasks()) {
                    throw new OutOfBounds(" There is no such element :-p");
                }
            } catch (OutOfBounds e3) {
                System.out.println(e3);
                break;
            }
            Task toDelete = taskList.getTask(number - 1);
            taskList.deleteTask(number - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + toDelete);
            System.out.println("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
            storage.updateFile(taskList);
            break;
        }
        }
    }

    /**
     * Checks if a task has an empty description.
     * @param checkString User input to be checked.
     * @return True or false depending on whether description of task is empty.
     * @throws EmptyDescription If the description of task is empty.
     */
    public static boolean checkEmptyDescription(String[] checkString) throws EmptyDescription {
        if (checkString.length == 1) {
            throw new EmptyDescription(" The description of " + checkString[0] + " cannot be empty.");
        }
        return true;
    }
}

