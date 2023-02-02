package Parser;

import Task.Deadline;
import Task.Event;
import Task.Task;
import TaskList.TaskList;
import Task.Todo;
import Exception.noTaskDescriptionException;
import Exception.invalidInputException;
import Exception.dukeException;
import Ui.Ui;

/**
 * Class to encapsulate all parser methods, which parse through user inputs.
 */
public class Parser {

    /**
     * Returns Task formed from given string.
     * If input string is not of recognised form, exception is thrown.
     *
     * @param echo Input string, treated as commands.
     * @return Task corresponding to given input commands.
     * @throws noTaskDescriptionException If command is given for a task without a description.
     * @throws invalidInputException If string does not start with recognised command.
     */
    public static Task parseEcho(String echo) throws dukeException {
        if (echo.startsWith("todo")) {
            if (echo.substring(4).trim().isEmpty()) {
                throw(new noTaskDescriptionException("     ☹ OOPS!!! The description of a todo cannot be empty."));
            }
            return new Todo(echo.substring(4).trim());

        } else if (echo.startsWith("deadline")) {
            String deadlineArguments = echo.substring(8).trim();
            if (deadlineArguments.isEmpty()) {
                throw(new noTaskDescriptionException("     ☹ OOPS!!! The description of a deadline cannot be empty."));
            }
            String splitArguments[] = deadlineArguments.split("/");

            return new Deadline(splitArguments[0], splitArguments[1].substring(2).trim());

        } else if (echo.startsWith("event")) {
            String eventArguments = echo.substring(5).trim();
            if (eventArguments.isEmpty()) {
                throw(new noTaskDescriptionException("     ☹ OOPS!!! The description of a event cannot be empty."));
            }
            String splitArguments[] = eventArguments.split("/");
            return new Event(splitArguments[0], splitArguments[1].substring(4).trim(),
                    splitArguments[2].substring(2).trim());

        } else {
            // System.out.println("Placeholder");
            throw(new invalidInputException("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
        }
    }

    /**
     * Returns a Task object corresponding to input String.
     * Made to be used to read from saved files, hence containing addition argument to check if tasks are done.
     *
     * @param echo Input string to be parsed
     * @param isDone Boolean to check if corresponding created Task is marked as done
     * @return Task corresponding to given String
     */
    public static Task parseFileReader(String echo, boolean isDone) {
        if (echo.startsWith("todo")) {
            return new Todo(isDone, echo.substring(4).trim());
        } else if (echo.startsWith("deadline")) {
            String deadlineArguments = echo.substring(8).trim();
            String splitArguments[] = deadlineArguments.split("/");
            return new Deadline(isDone, splitArguments[0], splitArguments[1].substring(2).trim());
        } else if (echo.startsWith("event")) {
            String eventArguments = echo.substring(5).trim();
            String splitArguments[] = eventArguments.split("/");
            return new Event(isDone, splitArguments[0], splitArguments[1].substring(4).trim(),
                    splitArguments[2].substring(2).trim());
        } else {
            throw(new invalidInputException("      ☹ OOPS!!! File format seems weird"));
        }
    }

    /**
     * Returns boolean based on whether a corresponding input string has a non-task creation command.
     * Attempts to carry out command if command is recognised, and returns true.
     * Else returns false.
     *
     * @param echo Input String to be parsed for commands.
     * @param tasks TaskList Object which encapsulates the current list of tasks.
     * @param ui Ui Object to call Ui-related methods.
     * @return Boolean if any tasks are recognised and carried out.
     */
    public static boolean parseCommands(String echo, TaskList tasks, Ui ui) {
        if (echo.equals("list")) {
            System.out.println("    OK, Here are the items in your list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + ". "
                        + tasks.get(i).toString());
            }
            // put in loop to read the list
            return true;
        }

        if (echo.startsWith("mark")) {
            try {
                int taskToModify = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                tasks.markDone(taskToModify-1);
            } catch (Exception e) {
                // TODO: handle exception
                return false;
            }
            return true;
        }

        if (echo.startsWith("unmark")) {
            try {
                int taskToModify = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                tasks.markUndone(taskToModify-1);
            } catch (Exception e) {
                // TODO: handle exception
                return false;
            }
            return true;
        }

        if (echo.startsWith("delete") || echo.startsWith("remove")) {
            try {
                int taskToModify = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                tasks.removeTask(taskToModify-1);
                ui.printListNumber(tasks.getList());
            } catch (Exception e) {
                // TODO: handle exception
                return false;
            }
            return true;
        }

        return false;
    }

}
