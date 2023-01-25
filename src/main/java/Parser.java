import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

    public boolean parseAndExecute(String commandString, TaskList taskList) {
        Scanner stringStream = new Scanner(commandString);
        String command = stringStream.next();

        if (command.equalsIgnoreCase("bye")) {
            return true;
        }else if (command.equalsIgnoreCase("list")) {
            handleList(taskList);
        } else if (command.equalsIgnoreCase("mark")) {
            handleMark(stringStream, taskList);
        } else if (command.equalsIgnoreCase("unmark")) {
            handleUnmark(stringStream, taskList);
        } else if (command.equalsIgnoreCase("deadline")) {
            handleDeadline(stringStream, taskList);
        } else if (command.equalsIgnoreCase("event")) {
            handleEvent(stringStream, taskList);
        } else if (command.equalsIgnoreCase("todo")) {
            handleToDo(stringStream, taskList);
        } else if (command.equalsIgnoreCase("delete")) {
            handleDelete(stringStream, taskList);
        } else {
            Duke.displayMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }

    private static void handleList(TaskList taskList) {
        String output = "";
        for (int i = 0; i != taskList.size();++i) {
            Task t = taskList.get(i);
            output += (i + 1) + ". " + t.toString() + "\n";
        }
        Duke.displayMessage(output);
    }

    private static void handleMark(Scanner stringStream, TaskList taskList) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    Duke.displayMessage("This task does not exist!");
                    return;
                }
                Task t = taskList.get(target);
                t.mark();
                String output = "I've marked this task as done!\n" + (target + 1) + ". " + t.toString();
                Duke.displayMessage(output);
                return;
            } catch (NumberFormatException nfe) {
                Duke.displayMessage("☹ OOPS!!! Please provide the number of the task to mark.");
            }
        } else {
            Duke.displayMessage("☹ OOPS!!! We weren't told which task to mark.");
        }
    }

    private static void handleUnmark(Scanner stringStream, TaskList taskList) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    Duke.displayMessage("This task does not exist!");
                    return;
                }
                Task t = taskList.get(target);
                t.unmark();
                String output = "I've marked this task as not done!\n" + (target + 1) + ". " + t.toString();
                Duke.displayMessage(output);
            } catch (NumberFormatException nfe) {
                Duke.displayMessage("☹ OOPS!!! Please provide the number of the task to unmark.");
            }
        } else {
            Duke.displayMessage("☹ OOPS!!! We weren't told which task to unmark.");
        }
    }

    private static void handleDelete(Scanner stringStream, TaskList taskList) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    Duke.displayMessage("This task does not exist!");
                    return;
                }
                Task t = taskList.get(target);
                taskList.remove(target);
                String output = "I've deleted this task!\n" + (target + 1) + ". " + t.toString();
                Duke.displayMessage(output);
            } catch (NumberFormatException nfe) {
                Duke.displayMessage("☹ OOPS!!! Please provide the number of the task to delete.");
            }
        } else {
            Duke.displayMessage("☹ OOPS!!! We weren't told which task to delete.");
        }
    }

    private static void handleDeadline(Scanner stringStream, TaskList taskList) {
        try {
            Deadline newTask = Deadline.parseDeadlineCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
            Duke.displayMessage(output);
        } catch (DukeException e) {
            Duke.displayMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            Duke.displayMessage("☹ OOPS!!! We couldn't figure out the entered date and time.\n" +
                    "Please use the format: dd/mm/yyyy hh:ss");
        }
    }

    private static void handleEvent(Scanner stringStream, TaskList taskList) {
        try {
            Event newTask = Event.parseEventCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
            Duke.displayMessage(output);
        } catch (DukeException e) {
            Duke.displayMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            Duke.displayMessage("☹ OOPS!!! We couldn't figure out the entered date and time.\n" +
                    "Please use the format: dd/mm/yyyy hh:ss");
        }
    }

    private static void handleToDo(Scanner stringStream, TaskList taskList) {
        try {
            ToDo newTask = ToDo.parseToDoCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
            Duke.displayMessage(output);
        } catch (DukeException e) {
            Duke.displayMessage(e.getMessage());
        }
    }

}
