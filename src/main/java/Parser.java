import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

    public boolean parseAndExecute(String commandString, TaskList taskList, Ui ui) {
        Scanner stringStream = new Scanner(commandString);
        String command = stringStream.next();

        if (command.equalsIgnoreCase("bye")) {
            return true;
        }else if (command.equalsIgnoreCase("list")) {
            handleList(taskList, ui);
        } else if (command.equalsIgnoreCase("mark")) {
            handleMark(stringStream, taskList, ui);
        } else if (command.equalsIgnoreCase("unmark")) {
            handleUnmark(stringStream, taskList, ui);
        } else if (command.equalsIgnoreCase("deadline")) {
            handleDeadline(stringStream, taskList, ui);
        } else if (command.equalsIgnoreCase("event")) {
            handleEvent(stringStream, taskList, ui);
        } else if (command.equalsIgnoreCase("todo")) {
            handleToDo(stringStream, taskList, ui);
        } else if (command.equalsIgnoreCase("delete")) {
            handleDelete(stringStream, taskList, ui);
        } else {
            ui.showMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }

    private static void handleList(TaskList taskList, Ui ui) {
        String output = "";
        for (int i = 0; i != taskList.size();++i) {
            Task t = taskList.get(i);
            output += (i + 1) + ". " + t.toString() + "\n";
        }
        ui.showMessage(output);
    }

    private static void handleMark(Scanner stringStream, TaskList taskList, Ui ui) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    ui.showMessage("This task does not exist!");
                    return;
                }
                Task t = taskList.get(target);
                t.mark();
                String output = "I've marked this task as done!\n" + (target + 1) + ". " + t.toString();
                ui.showMessage(output);
                return;
            } catch (NumberFormatException nfe) {
                ui.showMessage("☹ OOPS!!! Please provide the number of the task to mark.");
            }
        } else {
            ui.showMessage("☹ OOPS!!! We weren't told which task to mark.");
        }
    }

    private static void handleUnmark(Scanner stringStream, TaskList taskList, Ui ui) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    ui.showMessage("This task does not exist!");
                    return;
                }
                Task t = taskList.get(target);
                t.unmark();
                String output = "I've marked this task as not done!\n" + (target + 1) + ". " + t.toString();
                ui.showMessage(output);
            } catch (NumberFormatException nfe) {
                ui.showMessage("☹ OOPS!!! Please provide the number of the task to unmark.");
            }
        } else {
            ui.showMessage("☹ OOPS!!! We weren't told which task to unmark.");
        }
    }

    private static void handleDelete(Scanner stringStream, TaskList taskList, Ui ui) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    ui.showMessage("This task does not exist!");
                    return;
                }
                Task t = taskList.get(target);
                taskList.remove(target);
                String output = "I've deleted this task!\n" + (target + 1) + ". " + t.toString();
                ui.showMessage(output);
            } catch (NumberFormatException nfe) {
                ui.showMessage("☹ OOPS!!! Please provide the number of the task to delete.");
            }
        } else {
            ui.showMessage("☹ OOPS!!! We weren't told which task to delete.");
        }
    }

    private static void handleDeadline(Scanner stringStream, TaskList taskList, Ui ui) {
        try {
            Deadline newTask = Deadline.parseDeadlineCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
            ui.showMessage(output);
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.showMessage("☹ OOPS!!! We couldn't figure out the entered date and time.\n" +
                    "Please use the format: dd/mm/yyyy hh:ss");
        }
    }

    private static void handleEvent(Scanner stringStream, TaskList taskList, Ui ui) {
        try {
            Event newTask = Event.parseEventCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
            ui.showMessage(output);
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.showMessage("☹ OOPS!!! We couldn't figure out the entered date and time.\n" +
                    "Please use the format: dd/mm/yyyy hh:ss");
        }
    }

    private static void handleToDo(Scanner stringStream, TaskList taskList, Ui ui) {
        try {
            ToDo newTask = ToDo.parseToDoCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
            ui.showMessage(output);
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        }
    }

}
