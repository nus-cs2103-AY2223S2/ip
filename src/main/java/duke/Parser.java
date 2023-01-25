package duke;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Represents a parser that can read the input and execute the corresponding command
 */

public class Parser {

    /**
     * check if input string has less than 2 words *
     * 
     * @param splitInput Array of words in input string
     * @return true if splitInput < 2 else false
     */
    
    public static boolean checkDescription(String[] splitInput) {
        return splitInput.length < 2;
    }

    /**
     * Convert input string to LocalDate type
     * @param splitInput Array of words in input string
     * @return LocalDate object of parsed input string else null
     */
    
    public static LocalDate getDate(String[] splitInput) {
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

    /**
     * parses Input and execute corresponding command
     * @param input User string input
     * @param TaskList list of tasks
     * @return true if the command requires saving to storage else false
     * @throws DukeException If command cannot be understood
     */

    public boolean parse(String input, TaskList toDoList) throws DukeException {
        String[] splitInput = input.split(" ");
        String command = splitInput[0];
        boolean writeToFile = false;
        LocalDate taskDate;

        System.out.println("________________________________");

        switch (command) {
            case "find":
                String searchString = input.substring(("find").length() + 1);
                ArrayList<Task> foundTasks = toDoList.search(searchString);
                System.out.println("Here are the matching tasks in your list:");
                for (int i = 0; i < foundTasks.size(); i++) {
                    System.out.println(i + 1 + "." + foundTasks.get(i).toString());
                }
                break;

            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < toDoList.size(); i++) {

                    System.out.println(i + 1 + "." + toDoList.get(i).toString());
                }
                break;

            case "mark":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The value cannot be empty.");
                }

                String taskNumMark = splitInput[1];
                Task taskToMark;
                try {
                    taskToMark = toDoList.get(Integer.parseInt(taskNumMark) - 1);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + taskToMark.mark());

                } catch (NumberFormatException e) {
                    throw new DukeException("Please input an integer");

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please input a valid integer");
                }
                writeToFile = true;

                break;
            case "unmark":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The value cannot be empty.");
                }

                try {
                    String taskNumUnmark = splitInput[1];

                    Task taskToUnmark = toDoList.get(Integer.parseInt(taskNumUnmark) - 1);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(" " + taskToUnmark.unMark());

                } catch (NumberFormatException e) {
                    throw new DukeException("Please input an integer");

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please input a valid integer");
                }
                writeToFile = true;

                break;
            case "event":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }

                taskDate = getDate(splitInput);
                String eventDescription;
                System.out.println(splitInput[1]);

                if (taskDate == null) {
                    eventDescription = input.substring(("event").length() + 1);
                } else {

                    eventDescription = String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length - 2));
                }

                Task newEvent = new Event(eventDescription, taskDate);
                toDoList.add(newEvent);
                System.out.println(" Got it. I've added this task:");
                System.out.println("  " + newEvent.toString());
                System.out.println("Now you have " + toDoList.size() + " tasks on the list.");
                writeToFile = true;

                break;
            case "deadline":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                taskDate = getDate(splitInput);
                String deadlineDescription;
                if (taskDate == null) {
                    deadlineDescription = input.substring(("deadline").length() + 1);
                } else {
                    deadlineDescription = String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length - 2));
                }

                Task newDeadline = new Deadline(deadlineDescription, taskDate);
                toDoList.add(newDeadline);

                System.out.println(" Got it. I've added this task:");
                System.out.println("  " + newDeadline.toString());
                System.out.println("Now you have " + toDoList.size() + " tasks on the list.");
                writeToFile = true;
                
                break;
            case "todo":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                taskDate = getDate(splitInput);

                String todoDescription;
                if (taskDate == null) {
                    todoDescription = input.substring(("todo").length() + 1);
                } else {
                    todoDescription = String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length - 2));
                }
                Task newTodo = new Todo(todoDescription, taskDate);
                toDoList.add(newTodo);
                System.out.println(" Got it. I've added this task:");
                System.out.println("  " + newTodo.toString());
                System.out.println("Now you have " + toDoList.size() + " tasks on the list.");
                writeToFile = true;
                
                break;
            case "delete":
                if (checkDescription(splitInput)) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                String taskDelete = splitInput[1];

                try {
                    Task taskToDelete = toDoList.get(Integer.parseInt(taskDelete) - 1);
                    toDoList.remove(Integer.parseInt(taskDelete) - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + taskToDelete.toString());
                    System.out.println("Now you have " + toDoList.size() + " tasks on the list.");

                } catch (NumberFormatException e) {
                    throw new DukeException("Please input an integer");

                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please input a valid integer");
                }
                writeToFile = true;
                
                break;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

        }

        return writeToFile;

    }

}
