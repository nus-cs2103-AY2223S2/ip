import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<Task>();
    public static Scanner sc = new Scanner(System.in);
    public static boolean exitApp = false;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        init();

        // App loop
        while (!exitApp) {
            update();
        }

        // Exit message
        displayMessage("Bye! Hope to see you again soon!");
    }

    public static void init() {
        displayMessage("Hello! I'm Duke\nWhat can i do for you?");
    }

    public static void update() {
        String input = sc.nextLine();
        if (input.isEmpty()) {
            return;
        }

        Scanner stringStream = new Scanner(input);
        String command = stringStream.next();


        if (command.equalsIgnoreCase("bye")) {
            exitApp = true;
            return;
        }else if (command.equalsIgnoreCase("list")) {
            handleList();
            return;
        } else if (command.equalsIgnoreCase("mark")) {
            handleMark(stringStream);
            return;
        } else if (command.equalsIgnoreCase("unmark")) {
            handleUnmark(stringStream);
            return;
        } else if (command.equalsIgnoreCase("deadline")) {
            handleDeadline(stringStream);
            return;
        } else if (command.equalsIgnoreCase("event")) {
            handleEvent(stringStream);
            return;
        } else if (command.equalsIgnoreCase("todo")) {
            handleToDo(stringStream);
            return;
        } else if (command.equalsIgnoreCase("delete")) {
            handleDelete(stringStream);
            return;
        } else {
            displayMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void handleList() {
        String output = "";
        for (int i = 0; i != taskList.size();++i) {
            Task t = taskList.get(i);
            output += (i + 1) + ". " + t.toString() + "\n";
        }
        displayMessage(output);
    }

    public static void handleMark(Scanner stringStream) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    displayMessage("This task does not exist!");
                    return;
                }
                Task t = taskList.get(target);
                t.mark();
                String output = "I've marked this task as done!\n" + t.toString();
                displayMessage(output);
                return;
            } catch (NumberFormatException nfe) {
                displayMessage("☹ OOPS!!! Please provide the number of the task to mark.");
            }
        } else {
            displayMessage("☹ OOPS!!! We weren't told which task to mark.");
        }
    }

    public static void handleUnmark(Scanner stringStream) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    displayMessage("This task does not exist!");
                    return;
                }
                Task t = taskList.get(target);
                t.unmark();
                String output = "I've marked this task as not done!\n" + t.toString();
                displayMessage(output);
            } catch (NumberFormatException nfe) {
                displayMessage("☹ OOPS!!! Please provide the number of the task to unmark.");
            }
        } else {
            displayMessage("☹ OOPS!!! We weren't told which task to unmark.");
        }
    }

    public static void handleDelete(Scanner stringStream) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    displayMessage("This task does not exist!");
                    return;
                }
                Task t = taskList.get(target);
                taskList.remove(target);
                String output = "I've deleted this task!\n" + t.toString();
                displayMessage(output);
            } catch (NumberFormatException nfe) {
                displayMessage("☹ OOPS!!! Please provide the number of the task to delete.");
            }
        } else {
            displayMessage("☹ OOPS!!! We weren't told which task to delete.");
        }
    }

    public static void handleDeadline(Scanner stringStream) {
        try {
            Deadline newTask = Deadline.parseDeadlineCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
            displayMessage(output);
        } catch (DukeException e) {
            displayMessage(e.getMessage());
        }
    }

    public static void handleEvent(Scanner stringStream) {
        try {
            Event newTask = Event.parseEventCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
            displayMessage(output);
        } catch (DukeException e) {
            displayMessage(e.getMessage());
        }
    }

    public static void handleToDo(Scanner stringStream) {
        try {
            ToDo newTask = ToDo.parseToDoCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
            displayMessage(output);
        } catch (DukeException e) {
            displayMessage(e.getMessage());
        }
    }
    public static void displayMessage(String msg) {

        String wrapTop = "__________________________\n";
        String wrapBottom = "\n__________________________";
        System.out.println(wrapTop + msg.toString() + wrapBottom);
    }
}
