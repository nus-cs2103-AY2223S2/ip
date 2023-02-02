package Parser;

import Deadline.Deadline;
import Event.Event;
import Task.Task;
import TaskList.TaskList;
import todo.todo;
import Exception.noTaskDescriptionException;
import Exception.invalidInputException;
import Exception.dukeException;
import Ui.Ui;

public class Parser {


    public static Task parseEcho(String echo) throws dukeException {
        if (echo.startsWith("todo")) {
            if (echo.substring(4).trim().isEmpty()) {
                throw(new noTaskDescriptionException("     ☹ OOPS!!! The description of a todo cannot be empty."));
            }
            return new todo(echo.substring(4).trim());

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
            return new todo(isDone, echo.substring(4).trim());
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

        return false;
    }

}
