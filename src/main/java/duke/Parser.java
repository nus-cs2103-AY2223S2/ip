package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import java.util.ArrayList;

/**
 * Encapsulates a parser that parses the user input in the Duke app.
 */
public class Parser {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Creates a new Parser object.
     * @param taskList The TaskList object containing the ArrayList of Tasks.
     */
    Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.ui = new Ui();
        this.storage = storage;
    }

    /**
     * Parses the input string and modifies the list accordingly
     * @param input The string input to be parsed.
     * @throws DukeException Throws a DukeException if the input is invalid.
     */
    public String parseInput(String input) throws DukeException {
        assert input != "": "Input cannot be empty";
        if (input.equals("bye")) {
            this.storage.save(taskList);
            return ui.goodbyeMessage();
        }
        if (input.equals("list")) {
            return ui.printList(taskList.getList());
        }
        String[] parsedCommand = input.split(" ");
        String cmd = parsedCommand[0];
        switch (cmd) {
        case "mark":
            return markParser(input);
        case "unmark":
            return unmarkParser(input);
        case "delete":
            return deleteParser(input);
        case "event":
            return eventParser(input);
        case "deadline":
            return deadlineParser(input);
        case "todo":
            return todoParser(input);
        case "find":
            return findParser(input);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


    public String markParser(String input) {
        try {
            markInputChecker(input);
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            taskList.markTaskAsDone(taskNum);
            return ui.markTaskAsDoneMessage(taskList.getTask(taskNum));
        } catch (DukeException e) {
            return ui.printMessage(e.getMessage());
        }
    }

    public String unmarkParser(String input) {
        try {
            unmarkInputChecker(input);
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            taskList.markTaskAsIncomplete(taskNum);
            return ui.markTaskAsIncompleteMessage(taskList.getTask(taskNum));
        } catch (DukeException e) {
            return ui.printMessage(e.getMessage());
        }
    }

    public String deleteParser(String input) {
        try {
            deleteInputChecker(input);
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            taskList.deleteTaskFromList(taskNum);
            return ui.deletedTaskMessage(taskList.getTask(taskNum), taskList.numberOfTasks());
        } catch (DukeException e) {
            return ui.printMessage(e.getMessage());
        }
    }

    public String eventParser(String input) {
        try {
            eventInputChecker(input);
        } catch (DukeException e) {
            return ui.printMessage(e.getMessage());
        }
        String[] eventConstructor = input.replace("event ", "").split("/at ");
        String timeModified = eventConstructor[1].replace("from ", "");
        return inputEvent(eventConstructor[0], timeModified);
    }

    public String deadlineParser(String input) {
        try {
            deadlineInputChecker(input);
            return addDeadlineFormatted(input);
        } catch (DukeException e) {
            return ui.printMessage(e.getMessage());
        }
    }

    public String todoParser(String input) {
        try {
            todoInputChecker(input);
        } catch (DukeException e) {
            return ui.printMessage(e.getMessage());
        }
        return inputTodo(input.replace("todo ", ""));
    }

    public String findParser(String input) {
        try {
            findTaskInputChecker(input);
            return findTasks(input);
        } catch (DukeException e) {
            return ui.printMessage(e.getMessage());
        }
    }

    /**
     * Checks if the input for a Todo task is valid.
     * @param input The input string.
     * @throws DukeException if the input is invalid.
     */
    public void todoInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
        }
    }

    /**
     * Checks if the input for an Event task is valid.
     * @param input The input string.
     * @throws DukeException if the input is invalid.
     */
    public void eventInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }
        }
    }

    /**
     * Checks if the input for a Deadline task is valid.
     * @param input The input string.
     * @throws DukeException if the input is invalid.
     */
    public void deadlineInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
        }
    }

    /**
     * Checks if the input to delete a task is valid.
     * @param input The input string.
     * @throws DukeException if the input is invalid.
     */
    public void deleteInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! You have to choose a task to delete.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! You have to choose a task to delete.");
            }
        }
    }

    /**
     * Checks if the input to mark a task is valid.
     * @param input The input string.
     * @throws DukeException if the input is invalid.
     */
    public void markInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! You have to choose a task to mark.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! You have to choose a task to mark.");
            }
        }
    }

    /**
     * Checks if the input to unmark a task is valid.
     * @param input The input string.
     * @throws DukeException if the input is invalid
     */
    public void unmarkInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! You have to choose a task to unmark.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! You have to choose a task to delete.");
            }
        }
    }

    /**
     * Checks if the input to find tasks is valid.
     * @param input the input string.
     * @throws DukeException if the input is invalid.
     */
    public void findTaskInputChecker(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        if (inputArray.length != 2) {
            throw new DukeException("OOPS!!! Invalid search term. Try adding a task description.");
        } else {
            if (inputArray[1].trim().length() == 0) {
                throw new DukeException("OOPS!!! Invalid search term. Try adding a task description.");
            }
        }
    }

    /**
     * Finds the tasks that have a description containing the query
     * @param input The input string that is used to find tasks.
     * @throws DukeException if the input string does not match any task descriptions.
     */
    public String findTasks(String input) throws DukeException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> taskNumbers = new ArrayList<>();
        try {
            for (int i = 1; i <= this.taskList.numberOfTasks(); i++) {
                if (this.taskList.getTask(i).description.toLowerCase()
                        .contains(input.replace("find ", "").toLowerCase())) {
                    taskNumbers.add(i);
                }
            }
            if (taskNumbers.size() == 0) {
                throw new DukeException("OOPS!!! No such task matches your description.");
            }
            sb.append(ui.findTasksMessage());
            sb.append(ui.printFoundTasks(this.taskList, taskNumbers));
        } catch (Exception e) {
            return ui.printMessage(e.getMessage());
        }
        return sb.toString();
    }

    /**
     * Adds a deadline object with the formatted date to the list.
     * @param input The input string
     * @throws DukeException if the format of the date is wrong.
     */
    public String addDeadlineFormatted(String input) throws DukeException {
        String[] constructor = input.replace("deadline ", "").split(" /by ");
        try {
            LocalDate temp = LocalDate.parse(constructor[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return inputDeadline(constructor[0], temp);

        } catch (Exception e) {
            throw new DukeException("Please input date in format of dd/MM/yyyy");
        }
    }

    /**
     * Inputs a new Event object into the list.
     * @param s The description of the event.
     * @param time The time of the event.
     */
    public String inputEvent(String s, String time) {
        Event event = new Event(s, time);
        taskList.add(event);
        return ui.addedTaskMessage(event, taskList.numberOfTasks());
    }

    /**
     * Inputs a new Deadline object into the list.
     * @param s The description of the deadline.
     * @param d The date of the deadline.
     */
    public String inputDeadline(String s, LocalDate d) {
        Deadline deadline = new Deadline(s, d);
        taskList.add(deadline);
        return ui.addedTaskMessage(deadline, taskList.numberOfTasks());
    }

    /**
     * Inputs a new Todo object into the list.
     * @param s The description of the todo task.
     */
    public String inputTodo(String s) {
        Todo todo = new Todo(s);
        taskList.add(todo);
        return ui.addedTaskMessage(todo, taskList.numberOfTasks());
    }
}
