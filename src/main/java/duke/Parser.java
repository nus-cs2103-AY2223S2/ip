package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Used to parse user input into recognised commands and execute them.
 */
public class Parser {

    /**
     * Parses the given user input (commandString) and executes the correct action based on that. Returns
     * a boolean to indicate if the application should close due to the user's input.
     *
     * @param commandString string that contains the user's input command
     * @param taskList a list that contains all tasks
     * @return true if the application should exit. Otherwise, returns false.
     */
    public static String parseAndExecute(String commandString, TaskList taskList) {
        Scanner stringStream = new Scanner(commandString);
        String command = stringStream.next();

        if (command.equalsIgnoreCase("bye")) {
            return "bye";
        } else if (command.equalsIgnoreCase("list")) {
            return handleList(taskList);
        } else if (command.equalsIgnoreCase("mark")) {
            return handleMark(stringStream, taskList);
        } else if (command.equalsIgnoreCase("unmark")) {
            return handleUnmark(stringStream, taskList);
        } else if (command.equalsIgnoreCase("deadline")) {
            return handleDeadline(stringStream, taskList);
        } else if (command.equalsIgnoreCase("event")) {
            return handleEvent(stringStream, taskList);
        } else if (command.equalsIgnoreCase("todo")) {
            return handleToDo(stringStream, taskList);
        } else if (command.equalsIgnoreCase("delete")) {
            return handleDelete(stringStream, taskList);
        } else if (command.equalsIgnoreCase("find")) {
            return handleFind(stringStream, taskList);
        } else {
            return Ui.genUnknownCommandMsg();
        }
    }

    private static String handleList(TaskList taskList) {
        String output = "";
        for (int i = 0; i != taskList.size(); ++i) {
            Task t = taskList.get(i);
            output += (i + 1) + ". " + t.toString() + "\n";
        }
        return Ui.genShowTaskListMsg(output);
    }

    private static String handleMark(Scanner stringStream, TaskList taskList) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    return Ui.genTaskDoesNotExistMsg();
                }
                Task t = taskList.get(target);
                t.mark();
                String output = "I've marked this task as done!\n" + (target + 1) + ". " + t.toString();
                return Ui.genMarkTaskMsg(true);
            } catch (NumberFormatException nfe) {
                return Ui.genMissingTaskNumberMsg();
            }
        } else {
            return Ui.genMissingTaskNumberMsg();
        }
    }

    private static String handleUnmark(Scanner stringStream, TaskList taskList) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    return Ui.genTaskDoesNotExistMsg();
                }
                Task t = taskList.get(target);
                t.unmark();
                String output = "I've marked this task as not done!\n" + (target + 1) + ". " + t.toString();
                return Ui.genMarkTaskMsg(false);
            } catch (NumberFormatException nfe) {
                return Ui.genMissingTaskNumberMsg();
            }
        } else {
            return Ui.genMissingTaskNumberMsg();
        }
    }

    private static String handleDelete(Scanner stringStream, TaskList taskList) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    return Ui.genTaskDoesNotExistMsg();
                }
                Task t = taskList.get(target);
                taskList.remove(target);
                String output = "I've deleted this task!\n" + (target + 1) + ". " + t.toString();
                return Ui.genDeleteTaskMsg();
            } catch (NumberFormatException nfe) {
                return Ui.genMissingTaskNumberMsg();
            }
        } else {
            return Ui.genMissingTaskNumberMsg();
        }
    }

    private static String handleDeadline(Scanner stringStream, TaskList taskList) {
        try {
            Deadline newTask = Deadline.parseDeadlineCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString()
                    + "\n" + "You now have " + taskList.size() + " tasks in the list";
            return Ui.genAddTaskMsg(output);
        } catch (DukeException e) {
            return Ui.genAddTaskMsg(e.getMessage());
        } catch (DateTimeParseException e) {
            return Ui.genAddTaskMsg("☹ OOPS!!! We couldn't figure out the entered date and time.\n"
                    + "Please use the format: dd/mm/yyyy hh:ss");
        }
    }

    private static String handleEvent(Scanner stringStream, TaskList taskList) {
        try {
            Event newTask = Event.parseEventCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have "
                    + taskList.size() + " tasks in the list";
            return Ui.genAddTaskMsg(output);
        } catch (DukeException e) {
            return Ui.genAddTaskMsg(e.getMessage());
        } catch (DateTimeParseException e) {
            return Ui.genAddTaskMsg("☹ OOPS!!! We couldn't figure out the entered date and time.\n"
                    + "Please use the format: dd/mm/yyyy hh:ss");
        }
    }

    private static String handleToDo(Scanner stringStream, TaskList taskList) {
        try {
            ToDo newTask = ToDo.parseToDoCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString()
                    + "\n" + "You now have " + taskList.size() + " tasks in the list";
            return Ui.genAddTaskMsg(output);
        } catch (DukeException e) {
            return Ui.genAddTaskMsg(e.getMessage());
        }
    }

    private static String handleFind(Scanner stringStream, TaskList taskList) {
        if (!stringStream.hasNext()) {
            return Ui.genMissingKeywordMsg();
        }

        String keyword = stringStream.next();
        TaskList tasksOfInterest = new TaskList();

        for (Task t : taskList) {
            String desc = t.getDescription();
            if (desc.contains(keyword)) {
                tasksOfInterest.add(t);
            }
        }
        return handleList(tasksOfInterest);
    }

}
