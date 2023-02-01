import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static String currentInput;
    private static TaskList taskList;
    private static Storage memory = new Storage("/duke.txt");
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static Ui ui = new Ui();

    public static String mark(boolean toMark) throws DukeException {
        int index = Integer.parseInt(toMark ? currentInput.substring(5) : currentInput.substring(7)) - 1;
        if (index >= taskList.size() || index < 0) {
            throw new DukeException("Task index out of bounds, please input a valid index");
        } else {
            Task curTask = taskList.getTask(index);
            curTask.setCompleted(toMark);
            String output;
            if (toMark) {
                output = "Nice! I've marked this task as done:\n";
            } else {
                output = "OK, I've marked this task as not done yet:\n";
            }
            memory.saveState(taskList);
            return output + "  " + curTask.toString();
        }
    }

    public static String addTask() throws DukeException {
        try {
            StringBuilder response = new StringBuilder();
            response.append("Got it. I've added this task:\n");
            if (currentInput.matches("^todo .*")) {
                //Adding a Todo task
                taskList.add(new Todo(currentInput.substring(5)));
            } else if (currentInput.matches("^deadline .*")) {
                //Adding a Deadline
                int byPos = currentInput.indexOf(" /by ");
                if (byPos == -1) {
                    throw new DukeException("Deadline not specified with /by");
                }
                String description = currentInput.substring(9, byPos);
                String by = currentInput.substring(byPos + 5);
                LocalDateTime convertedBy = LocalDateTime.parse(by, formatter);
                taskList.add(new Deadline(description, convertedBy));
            } else {
                //Adding an Event
                int fromPos = currentInput.indexOf(" /from ");
                int toPos = currentInput.indexOf(" /to ");
                if (fromPos == -1 || toPos == -1 || toPos > currentInput.length() + 4) {
                    throw new DukeException("Please include both /from and /to");
                }
                if (fromPos > toPos) {
                    throw new DukeException("Please add the from date first followed by to date");
                }
                if (fromPos == 5) {
                    throw new DukeException("Please include a description of the task");
                }
                String description = currentInput.substring(6, fromPos);
                String from = currentInput.substring(fromPos + 7, toPos);
                String to = currentInput.substring(toPos+ 5);
                LocalDateTime convertedFrom = LocalDateTime.parse(from, formatter);
                LocalDateTime convertedTo = LocalDateTime.parse(to, formatter);
                taskList.add(new Event(description, convertedFrom, convertedTo));
            }
            int count = taskList.size();
            response.append("  ").append(taskList.getTaskString(count - 1)).append("\n");
            response.append("Now you have ").append(count).append(" tasks in the list.");
            memory.saveState(taskList);
            return response.toString();
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date and Time provided, use the format: dd/MM/yyyy HH:mm");
        }
    }

    public static String deleteTask(String command) throws DukeException {
        int index = Integer.parseInt(command.substring(7)) - 1;
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("Error: Please input a valid task index!");
        } else {
            String output = taskList.removeTask(index);
            memory.saveState(taskList);
            return output;
        }
    }

    public static void main(String[] args) {

        taskList = new TaskList(memory.load());
        //Introduction
        ui.greet();
        currentInput = sc.nextLine();
        while (!currentInput.equalsIgnoreCase("bye")) {
            try {
                //when there is no input
                if (currentInput.equals("")) {
                    ui.reply("Please input a command");
                } else if (currentInput.equalsIgnoreCase("list")) {
                    ui.reply(taskList.toString());
                } else if (currentInput.matches("mark \\d+") || currentInput.matches("unmark \\d+")) {
                    boolean toMark = currentInput.matches("mark \\d+");
                    ui.reply(mark(toMark));
                } else if (currentInput.matches("^(todo|deadline|event) .*")) {
                    ui.reply(addTask());
                } else if (currentInput.matches("^delete \\d+")) {
                    ui.reply(deleteTask(currentInput));
                } else {
                    ui.reply("Unknown command, please try again");
                }
            } catch (DukeException e) {
                ui.reply(e.toString());
            }

            currentInput = sc.nextLine();
        }
        //Signing off
        ui.signOff();
    }
}
