package duke.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.DukeException;
import duke.logic.task.Deadline;
import duke.logic.task.Event;
import duke.logic.task.Task;
import duke.logic.task.Todo;

/**
 * Parser class takes in commands and executes them.
 */
public class Parser {

    /**
     * Splits input into command and content strings.
     * @param input Input String.
     * @return String array of size 2 containing command and content.
     */
    public static String[] parse(String input) {
        String[] words = input.split(" ");
        String command = words[0];
        StringBuilder desc = new StringBuilder();
        desc.append(" ");
        for (int i = 1; i < words.length; i++) {
            desc.append(words[i]);
            if (i < words.length - 1) {
                desc.append(" ");
            }
        }
        return new String[]{command, desc.toString()};
    }

    /**
     * Executes the appropriate command based on the input given by the user, which is split into
     * a command and content before being parsed and executed.
     *
     * @param command Command word to be executed.
     * @param content Details of the command to be executed.
     * @param taskList TaskList objects which the command should be executed on.
     * @return String response based on the command executed.
     */
    public static String execute(String command, String content, TaskList taskList) {
        String response;
        if (command.equals("list")) {
            response = "Here are the tasks in your list\n" + taskList.toString();
        } else if (command.equals("todo")) {
            try {
                Task task = Todo.create(content);
                taskList.addTask(task);
                response = "Ok boss. Added task:\n" + task.toString()
                        + "\nNow you have " + taskList.getSize() + " in the list.";
            } catch (DukeException e) {
                response = "☹ OOPS!!! The description of a todo cannot be empty.";
            }

        } else if (command.equals("deadline")) {
            try {
                Task task = Deadline.create(content);
                taskList.addTask(task);
                response = "Ok boss. Added task:\n" + task.toString()
                        + "\nNow you have " + taskList.getSize() + " in the list.";
            } catch (DukeException e) {
                response = "☹ OOPS!!! Invalid input for deadline.";
            }

        } else if (command.equals("event")) {
            try {
                Task task = Event.create(content);
                taskList.addTask(task);
                response = "Ok boss. Added task:\n" + task.toString()
                        + "\nNow you have " + taskList.getSize() + " in the list.";
            } catch (DukeException e) {
                response = "☹ OOPS!!! Invalid input for event.";
            }

        } else if (command.equals("mark")) {
            if (content.length() < 2) {
                response = "☹ OOPS!!! Invalid input for mark command.";
            } else {
                int index = Integer.parseInt(content.substring(1)) - 1;
                if (index >= taskList.getSize() || index < 0) {
                    response = "☹ OOPS!!! No such task in list.";
                } else {
                    Task task = taskList.getTask(index);
                    task.markDone();
                    response = "Ok boss! Marked this task as done: \n" + task.toString();
                }
            }

        } else if (command.equals("unmark")) {
            if (content.length() < 2) {
                response = "☹ OOPS!!! Invalid input for unmark command.";
            } else {
                int index = Integer.parseInt(content.substring(1)) - 1;
                if (index >= taskList.getSize() || index < 0) {
                    response = "☹ OOPS!!! No such task in list.";
                } else {
                    Task task = taskList.getTask(index);
                    task.unmarkDone();
                    response = "Ok boss! Marked this task as not done yet: \n" + task.toString();
                }
            }

        } else if (command.equals("delete")) {
            if (content.length() < 2) {
                response = "☹ OOPS!!! Invalid input for delete command.";
            } else {
                int index = Integer.parseInt(content.substring(1)) - 1;
                if (index >= taskList.getSize() || index < 0) {
                    response = "☹ OOPS!!! No such task in list.";
                } else {
                    Task task = taskList.deleteTask(index);
                    response = "Ok boss! task removed: \n" + task.toString()
                            + "\nNow you have " + taskList.getSize() + " in the list.";
                }
            }

        } else if (command.equals("find")) {
            if (content.length() < 1) {
                response = "☹ OOPS!!! Invalid keyword for find command.";
                return response;
            }

            String keyWord = content.substring(1);
            ArrayList<Integer> arrayList = taskList.findIndexesContaining(keyWord);
            if (arrayList.size() == 0) {
                response = "no matches found!";
            } else {
                response = "Here are the matching tasks from your list:\n"
                        + taskList.toStringIndexes(arrayList);
            }


        } else if (command.equals("schedule")) {
            if (content.length() < 1) {
                response = "☹ OOPS!!! Invalid keyword for schedule command.";
                return response;
            }

            try {
                String date = content.substring(1);
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ArrayList<Integer> arrayList = taskList.findDates(localDate);
                if (arrayList.size() == 0) {
                    response = "no matches found!";
                } else {
                    response = "Here are the tasks you have on the date " + localDate + " :\n"
                            + taskList.toStringIndexes(arrayList);
                }
            } catch (Exception e) {
                response = "☹ OOPS!!! Invalid keyword for schedule command.";
            }

        } else {
            response = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }

        System.out.println(response);
        return response;
    }
}
