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

import java.util.ArrayList;

public class Parser {


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
            return new Event(splitArguments[0], splitArguments[1].substring(4).trim(), splitArguments[2].substring(2).trim());

        } else {
            // System.out.println("Placeholder");
            throw(new invalidInputException("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
        }
    }

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
            return new Event(isDone, splitArguments[0], splitArguments[1].substring(4).trim(), splitArguments[2].substring(2).trim());
        } else {
            throw(new invalidInputException("      ☹ OOPS!!! File format seems weird"));
        }
    }

    public static boolean parseCommands(String echo, TaskList tasks, Ui ui) {
        if (echo.equals("list")) {
            System.out.println("    OK, Here are the items in your list: ");
            Ui.printArrayList(tasks.getList());
            // put in loop to read the list
            return true;
        }

        if (echo.startsWith("mark")) {
            try {
                int taskToModify = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                tasks.markDone(taskToModify-1);
            } catch (Exception e) {
                // TODO: handle exception
            }
            return true;
        }

        if (echo.startsWith("unmark")) {
            try {
                int taskToModify = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                tasks.markUndone(taskToModify-1);
            } catch (Exception e) {
                // TODO: handle exception
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
            }
            return true;
        }

        if (echo.startsWith("find")) {
            String taskToFind = echo.substring(4).trim();
            if (taskToFind.isEmpty()) {
                System.out.println("    OPPS!! You can't find nothing");
                return true;
            }
            ArrayList<Task> foundList = tasks.findArray(taskToFind);
            System.out.println("    Here are the matching tasks in your list:");
            Ui.printArrayList(foundList);
            return true;
        }

        return false;
    }

}
