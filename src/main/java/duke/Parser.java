package duke;

import java.time.LocalDate;

import java.util.Arrays;
import java.util.ArrayList;

import java.lang.StringBuilder;

/**
 * Represents a parser that can read the input and execute the corresponding
 * command
 */

public class Parser {

    /**
     * check if input string has less than 2 words *
     * 
     * @param splitInput Array of words in input string
     * @return true if splitInput < 2 else false
     */

    private static boolean checkDescription(String[] splitInput) {
        return splitInput.length < 2;
    }

    /**
     * convert input string to LocalDate type
     * 
     * @param splitInput Array of words in input string
     * @return LocalDate object of parsed input string else null
     */

    private static LocalDate getDate(String[] splitInput) {
        if (splitInput.length >= 4) {
            if (splitInput[splitInput.length - 2].equals("/by")) {
                try {
                    // in form yyyy-mm-dd
                    LocalDate taskDateTime = LocalDate
                            .parse(splitInput[splitInput.length - 1]);
                    return taskDateTime;
                } catch (Exception e) {
                    System.out.println("failed to read date");
                }
            }
        }
        return null;
    }

    private String findTask(String input, TaskList toDoList) {
        StringBuilder response = new StringBuilder();

        String searchString = input.substring(("find").length() + 1);
        ArrayList<Task> foundTasks = toDoList.search(searchString);
        response.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            response.append(i + 1 + "." + foundTasks.get(i).toString() + "\n");
        }
        return response.toString();
    }

    private String getUpComingDeadlines(TaskList toDoList) {
        StringBuilder response = new StringBuilder();

        response.append("Here are the upcoming deadlines");
        int counter = 1;
        for (int i = 0; i < toDoList.size(); i++){
            if(toDoList.get(i).isUpcomingDeadline()){
                response.append(counter + 1 + "." + toDoList.get(i).toString() + "\n");
                counter += 1;
            }

        }

        return response.toString();
    }

    private String listTasks(TaskList toDoList) {
        StringBuilder response = new StringBuilder();

        response.append("Here are the tasks in your list:\n");
        for (int i = 0; i < toDoList.size(); i++) {

            response.append(i + 1 + "." + toDoList.get(i).toString() + "\n");
        }
        return response.toString();
    }

    private String markTask(String[] splitInput, TaskList toDoList) throws DukeException {
        StringBuilder response = new StringBuilder();

        if (checkDescription(splitInput)) {
            throw new DukeException("OOPS!!! The value cannot be empty.");
        }

        String taskNumMark = splitInput[1];
        Task taskToMark;

        try {
            taskToMark = toDoList.get(Integer.parseInt(taskNumMark) - 1);

            response.append("Nice! I've marked this task as done:" + "\n");
            response.append(" " + taskToMark.mark() + "\n");

        } catch (NumberFormatException e) {
            throw new DukeException("Please input an integer");

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please input a valid integer");
        }

        return response.toString();
    }

    private String unMarkTask(String[] splitInput, TaskList toDoList) throws DukeException {
        StringBuilder response = new StringBuilder();

        if (checkDescription(splitInput)) {
            throw new DukeException("OOPS!!! The value cannot be empty.");
        }

        try {
            String taskNumUnmark = splitInput[1];

            Task taskToUnmark = toDoList.get(Integer.parseInt(taskNumUnmark) - 1);

            response.append("OK, I've marked this task as not done yet:\n");
            response.append(" " + taskToUnmark.unMark() + "\n");

        } catch (NumberFormatException e) {
            throw new DukeException("Please input an integer");

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please input a valid integer");
        }

        return response.toString();
    }

    private String createEvent(String[] splitInput, String input, TaskList toDoList) throws DukeException {

        StringBuilder response = new StringBuilder();

        if (checkDescription(splitInput)) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }

        LocalDate taskDate = Parser.getDate(splitInput);
        String eventDescription;

        if (taskDate == null) {
            eventDescription = input.substring(("event").length() + 1);
        } else {

            eventDescription = String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length - 2));
        }

        Task newEvent = new Event(eventDescription, taskDate);
        toDoList.add(newEvent);

        response.append(" Got it. I've added this task:\n");
        response.append("  " + newEvent.toString() + "\n");
        response.append("Now you have " + toDoList.size() + " tasks on the list.\n");

        return response.toString();

    }

    private String createDeadline(String[] splitInput, String input, TaskList toDoList) throws DukeException {
        StringBuilder response = new StringBuilder();

        if (checkDescription(splitInput)) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }

        LocalDate taskDate = Parser.getDate(splitInput);
        String deadlineDescription;
        if (taskDate == null) {
            deadlineDescription = input.substring(("deadline").length() + 1);
        } else {
            deadlineDescription = String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length - 2));
        }

        Task newDeadline = new Deadline(deadlineDescription, taskDate);
        toDoList.add(newDeadline);

        response.append(" Got it. I've added this task:\n");
        response.append("  " + newDeadline.toString() + "\n");
        response.append("Now you have " + toDoList.size() + " tasks on the list.\n");

        return response.toString();
    }

    private String createToDo(String[] splitInput, String input, TaskList toDoList) throws DukeException {
        StringBuilder response = new StringBuilder();

        if (checkDescription(splitInput)) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        LocalDate taskDate = Parser.getDate(splitInput);

        String todoDescription;
        if (taskDate == null) {
            todoDescription = input.substring(("todo").length() + 1);
        } else {
            todoDescription = String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length - 2));
        }
        Task newTodo = new Todo(todoDescription, taskDate);
        toDoList.add(newTodo);
        response.append(" Got it. I've added this task:\n");
        response.append("  " + newTodo.toString() + "\n");
        response.append("Now you have " + toDoList.size() + " tasks on the list.\n");

        return response.toString();
    }

    private String deleteTask(String[] splitInput, String input, TaskList toDoList) throws DukeException {

        StringBuilder response = new StringBuilder();

        if (checkDescription(splitInput)) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }

        String taskDelete = splitInput[1];

        try {
            Task taskToDelete = toDoList.get(Integer.parseInt(taskDelete) - 1);
            toDoList.remove(Integer.parseInt(taskDelete) - 1);
            response.append("Noted. I've removed this task:\n");
            response.append("  " + taskToDelete.toString() + "\n");
            response.append("Now you have " + toDoList.size() + " tasks on the list.\n");

        } catch (NumberFormatException e) {
            throw new DukeException("Please input an integer");

        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please input a valid integer");
        }

        return response.toString();
    }

    /**
     * parses Input and execute corresponding command
     * 
     * @param input    User string input
     * @param TaskList list of tasks
     * @return true if the command requires saving to storage else false
     * @throws DukeException If command cannot be understood
     */

    public String parseInput(String input, TaskList toDoList) {
        String[] splitInput = input.split(" ");
        String command = splitInput[0];

        switch (command) {
            case "upcoming" :
                return getUpComingDeadlines(toDoList);

            case "find":
                return findTask(input, toDoList);

            case "list":

                return listTasks(toDoList);

            case "mark":

                try {
                    return markTask(splitInput, toDoList);
                } catch (DukeException e) {

                    return e.getMessage();
                }

            case "unmark":

                try {
                    return unMarkTask(splitInput, toDoList);
                } catch (DukeException e) {

                    return e.getMessage();
                }

            case "event":
                try {
                    return createEvent(splitInput, input, toDoList);
                } catch (DukeException e) {

                    return e.getMessage();
                }

            case "deadline":
                try {
                    return createDeadline(splitInput, input, toDoList);
                } catch (DukeException e) {
                    return e.getMessage();
                }

            case "todo":
                try {
                    return createToDo(splitInput, input, toDoList);
                } catch (DukeException e) {
                    return e.getMessage();
                }

            case "delete":
                try {
                    return deleteTask(splitInput, input, toDoList);
                } catch (DukeException e) {
                    return e.getMessage();
                }
            case "bye":

                return "goodbye";

            default:
                return ("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

}
