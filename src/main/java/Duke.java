import java.util.ArrayList;
import java.util.Scanner;

/*
May want to catch NumberFormatException for Integer.parseInt
Prevent empty task desc
Prevent empty task specific data
 */

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
        }

        taskList.add(new Task(input));
        displayMessage("Added " + input);
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
    }

    public static void handleUnmark(Scanner stringStream) {
        int target = Integer.parseInt(stringStream.next()) - 1;
        if (target < 0 || target >= taskList.size()) {
            displayMessage("This task does not exist!");
            return;
        }
        Task t = taskList.get(target);
        t.unmark();
        String output = "I've marked this task as not done!\n" + t.toString();
        displayMessage(output);
    }

    public static void handleDeadline(Scanner stringStream) {
        String taskDesc = "";
        String by = "";
        boolean foundBy = false;
        while (stringStream.hasNext()) {
            String temp = stringStream.next();
            if (temp.equalsIgnoreCase("/by")) {
                foundBy = true;
                continue;
            }

            if (foundBy) {
                by += temp + " ";
            } else {
                taskDesc += temp + " ";
            }
        }

        if (taskDesc.isEmpty()) {
            String output = "☹ OOPS!!! The description of a deadline cannot be empty.";
            displayMessage(output);
            return;
        }

        if (!foundBy || by.isEmpty()) {
            String output = "☹ OOPS!!! Deadline tasks require a /by.";
            displayMessage(output);
            return;
        }

        Deadline newTask = new Deadline(taskDesc.trim(), by.trim());
        taskList.add(newTask);
        String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
        displayMessage(output);
    }

    public static void handleEvent(Scanner stringStream) {
        String taskDesc = "";
        String from = "";
        String to = "";

        boolean foundFrom = false;
        boolean foundTo = false;

        while (stringStream.hasNext()) {
            String temp = stringStream.next();

            if (temp.equalsIgnoreCase("/from")) {
                foundFrom = true;
                continue;
            } else if (temp.equalsIgnoreCase("/to")) {
                foundTo = true;
                continue;
            }

            if (foundTo) {
                to += temp + " ";
            } else if (foundFrom) {
                from += temp + " ";
            } else {
                taskDesc += temp + " ";
            }
        }

        if (taskDesc.isEmpty()) {
            String output = "☹ OOPS!!! The description of an event cannot be empty.";
            displayMessage(output);
            return;
        }

        if (!foundFrom || !foundTo || from.isEmpty() || to.isEmpty()) {
            String output = "☹ OOPS!!! Event tasks require a /from and /to.";
            displayMessage(output);
            return;
        }

        Event newTask = new Event(taskDesc.trim(), from.trim(), to.trim());
        taskList.add(newTask);
        String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
        displayMessage(output);
    }

    public static void handleToDo(Scanner stringStream) {
        String taskDesc = "";

        while (stringStream.hasNext()) {
            String temp = stringStream.nextLine();
            taskDesc += temp;
        }

        if (taskDesc.isEmpty()) {
            String output = "☹ OOPS!!! The description of a ToDo cannot be empty.";
            displayMessage(output);
            return;
        }

        ToDo newTask = new ToDo(taskDesc.trim());
        taskList.add(newTask);
        String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
        displayMessage(output);
    }
    public static void displayMessage(String msg) {

        String wrapTop = "__________________________\n";
        String wrapBottom = "\n__________________________";
        System.out.println(wrapTop + msg.toString() + wrapBottom);
    }
}
