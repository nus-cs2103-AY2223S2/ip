package parser;

import exception.DukeException;
import task.Task;
import task.TaskList;

import java.time.LocalDate;

public class Parser {

    /**
     * Returns true if a String is numerical; return false otherwise.
     *
     */
    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the number of '/' characters in the command.
     *
     * @return The number of '/' characters in the command
     */
    public static int countSlash(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '/') {
                count ++;
            }
        }
        return count;
    }

    /**
     * Executes the mark command by marking the task as done.
     *
     * @param taskList The tasklist that the task is in
     * @param t The task to be marked as done
     * @throws DukeException
     */
    public static void markCommand(TaskList taskList, Task t) throws DukeException {
        taskList.markTask(t);
    }

    /**
     * Executes the unmark command by unmarking the task.
     *
     * @param taskList The tasklist that the task is in
     * @param t The task to be marked as undone
     * @throws DukeException
     */
    public static void unmarkCommand(TaskList taskList, Task t) throws DukeException {
        taskList.unmarkTask(t);
    }

    /**
     * Executes the list command by listing the tasks in the tasklist.
     *
     * @param taskList The tasklist to be listed out
     */
    public static void listCommand(TaskList taskList) {
        if (taskList.getNumTasks() == 0) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(taskList);
        }
    }

    /**
     * Executes the todo command.
     *
     * @param tasklist The tasklist to add the todo
     * @param desc The title of the todo
     */
    public static void todoCommand(TaskList tasklist, String desc) throws DukeException {
        System.out.println("Got it, I've added this task:");
        tasklist.addTodo(desc);
    }

    /**
     * Executes the deadline command.
     *
     * @param taskList The tasklist to add the deadline
     * @param desc The title of the deadline
     * @param date The time/date of the deadline
     */

    public static void deadlineCommand(TaskList taskList, String desc, String date) {
        // convert string date to localdate
        LocalDate localDate = LocalDate.parse(date);
        System.out.println("Got it, I've added this task:");
        taskList.addDeadline(localDate, desc);
    }

    /**
     * Executes the event command.
     *
     * @param taskList The tasklist to add the event
     * @param start The start time/date of the event
     * @param end The end time/date of the event
     * @param desc The title of the event
     */
    public static void eventCommand(TaskList taskList, String start, String end, String desc) throws DukeException {
        System.out.println("Got it, I've added this task:");
        LocalDate startLocalDate = LocalDate.parse(start);
        LocalDate endLocalDate = LocalDate.parse(end);
        taskList.addEvent(startLocalDate, endLocalDate, desc);
    }

    /**
     * Executes the delete command.
     *
     * @param taskList The tasklist to delete the task from
     * @param taskNum The task number of the task to be deleted
     */
    public static void deleteCommand(TaskList taskList, int taskNum) throws DukeException {
        System.out.println("Noted. I've removed this task:");
        taskList.deleteTask(taskNum);
    }

    /**
     * Executes the next command.
     */
    public static void nextCommand() {
        System.out.println("What else can I do for you?");
    }

    /**
     * Executes the bye command.
     */
    public static void byeCommand() {
        System.out.println("Bye. Hope to see you again soon! :-p");
    }

    /**
     * Checks if the command is in the right format in order to execute the mark command.
     *
     */
    public static void checkMark(TaskList taskList, String command) throws DukeException {
        String arr[] = command.split("\\s+");
        if (arr.length == 2 && isNumber(arr[1]) && (arr[0].equals("mark") || arr[0].equals("unmark"))) {
            // check if task exists
            int taskNum = Integer.parseInt(arr[1]);
            if (taskList.doesTaskExist(taskNum)) {
                // mark or unmark task
                if (arr[0].equals("mark")) {
                    markCommand(taskList, taskList.getTask(taskNum - 1));
                } else if (arr[0].equals("unmark")){
                    unmarkCommand(taskList, taskList.getTask(taskNum - 1));
                }
            } else {
                throw new DukeException("Huh... the task does not exist.");
            }
        } else {
            throw new DukeException("Hmm... I can't quite understand you :-/");
        }
    }

    /**
     * Checks if the command is in the right format to execute a delete command.
     * @param taskList
     * @param command
     * @throws DukeException
     */
    public static void checkDelete(TaskList taskList, String command) throws DukeException {
        String arr[] = command.split("\\s+");
        if (arr.length == 2 && arr[0].equals("delete")) {
            if (isNumber(arr[1])) {
                if (taskList.doesTaskExist(Integer.parseInt(arr[1]))) {
                    deleteCommand(taskList, Integer.parseInt(arr[1]));
                } else {
                    throw new DukeException("Huh... the task does not exist.");
                }
            } else {
                throw new DukeException("Oops! You need to specify the task number for me to delete it.");
            }
        } else {
            throw new DukeException("Hmm... I can't quite understand you :-/");
        }
    }

    /**
     * Checks the command in order to execute the corresponding command correctly.
     */
    public static void checkCommand(TaskList taskList, String command) throws DukeException {
        String arr[] = command.split("\\s+");
        if (arr.length == 1) {
            if (arr[0].equals("todo") || arr[0].equals("event") || arr[0].equals("deadline")) {
                String e = String.format("Oops! The description of a %s cannot be empty.", arr[0]);
                throw new DukeException(e);
            } else if (command.equals("mark") || command.equals("unmark")
                    || command.equals("mark ") || command.equals("unmark ") || command.equals("delete") || command.equals("delete ")) {
                String e = String.format("Oops! You need to specify the task number for me to %s it.", command);
                throw new DukeException(e);
            } else {
                throw new DukeException("Hmm... I can't quite understand you :-/");
            }
        } else if (arr[0].equals("mark") || arr[0].equals("unmark")) {
            checkMark(taskList, command);
        } else {
            if (arr[0].equals("todo")) {
                String desc = command.split(" ", 2)[1];
                todoCommand(taskList, desc);
            } else if (arr[0].equals("deadline")) {
                if (!command.contains("/by")) {
                    throw new DukeException("Please specify the deadline.");
                } else {
                    String segments[] = command.split("/by ", 2);
                    String desc = segments[0].split("deadline ", 2)[1];
                    String date = segments[1];
                    deadlineCommand(taskList, desc, date);
                }
            } else if (arr[0].equals("event")) {
                if (!command.contains("/from") && !command.contains("/to")) {
                    throw new DukeException("Please specify both the start and end times/dates.");
                } else {
                    String segments[] = command.split("/from ", 2);
                    String desc = segments[0];
                    String start = segments[1].split(" /to")[0];
                    String end = segments[1].split("/to ")[1];
                    eventCommand(taskList, start, end, desc);
                }
            } else if (arr[0].equals("delete")) {
                checkDelete(taskList, command);
            } else {
                throw new DukeException("Hmm... I can't quite understand you :-/");
            }
        }
    }

}
