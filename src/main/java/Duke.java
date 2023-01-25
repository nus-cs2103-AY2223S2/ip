import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    private static Scanner sc = new Scanner(System.in);
    private static boolean exitApp = false;
    private static String storagePath = "data.txt";

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
        writeToFile(storagePath);
        displayMessage("Bye! Hope to see you again soon!");
    }

    private static void init() {
        readFromFile(storagePath);
        displayMessage("Hello! I'm Duke\nWhat can i do for you?");
    }

    private static void update() {
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

    private static void handleList() {
        String output = "";
        for (int i = 0; i != taskList.size();++i) {
            Task t = taskList.get(i);
            output += (i + 1) + ". " + t.toString() + "\n";
        }
        displayMessage(output);
    }

    private static void handleMark(Scanner stringStream) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    displayMessage("This task does not exist!");
                    return;
                }
                Task t = taskList.get(target);
                t.mark();
                String output = "I've marked this task as done!\n" + (target + 1) + ". " + t.toString();
                displayMessage(output);
                return;
            } catch (NumberFormatException nfe) {
                displayMessage("☹ OOPS!!! Please provide the number of the task to mark.");
            }
        } else {
            displayMessage("☹ OOPS!!! We weren't told which task to mark.");
        }
    }

    private static void handleUnmark(Scanner stringStream) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    displayMessage("This task does not exist!");
                    return;
                }
                Task t = taskList.get(target);
                t.unmark();
                String output = "I've marked this task as not done!\n" + (target + 1) + ". " + t.toString();
                displayMessage(output);
            } catch (NumberFormatException nfe) {
                displayMessage("☹ OOPS!!! Please provide the number of the task to unmark.");
            }
        } else {
            displayMessage("☹ OOPS!!! We weren't told which task to unmark.");
        }
    }

    private static void handleDelete(Scanner stringStream) {
        if (stringStream.hasNext()) {
            try {
                int target = Integer.parseInt(stringStream.next()) - 1;
                if (target < 0 || target >= taskList.size()) {
                    displayMessage("This task does not exist!");
                    return;
                }
                Task t = taskList.get(target);
                taskList.remove(target);
                String output = "I've deleted this task!\n" + (target + 1) + ". " + t.toString();
                displayMessage(output);
            } catch (NumberFormatException nfe) {
                displayMessage("☹ OOPS!!! Please provide the number of the task to delete.");
            }
        } else {
            displayMessage("☹ OOPS!!! We weren't told which task to delete.");
        }
    }

    private static void handleDeadline(Scanner stringStream) {
        try {
            Deadline newTask = Deadline.parseDeadlineCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
            displayMessage(output);
        } catch (DukeException e) {
            displayMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            displayMessage("☹ OOPS!!! We couldn't figure out the entered date and time.\n" +
                    "Please use the format: dd/mm/yyyy hh:ss");
        }
    }

    private static void handleEvent(Scanner stringStream) {
        try {
            Event newTask = Event.parseEventCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
            displayMessage(output);
        } catch (DukeException e) {
            displayMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            displayMessage("☹ OOPS!!! We couldn't figure out the entered date and time.\n" +
                    "Please use the format: dd/mm/yyyy hh:ss");
        }
    }

    private static void handleToDo(Scanner stringStream) {
        try {
            ToDo newTask = ToDo.parseToDoCommand(stringStream);
            taskList.add(newTask);
            String output = "I've added this task:\n" + newTask.toString() + "\n" + "You now have " + taskList.size() + " tasks in the list";
            displayMessage(output);
        } catch (DukeException e) {
            displayMessage(e.getMessage());
        }
    }

    private static void writeToFile(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(taskListToString());
            fw.close();
        } catch (IOException e) {
            displayMessage(e.getMessage());
        }

    }
    public static void readFromFile(String filePath) {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String taskString = s.nextLine();
                String[] parts = taskString.split("/");

                try {
                    if (parts[0].equals("D")) {
                        taskList.add(Deadline.parseDeadlineStringArray(parts));
                    } else if (parts[0].equals("E")) {
                        taskList.add(Event.parseEventStringArray(parts));
                    } else if (parts[0].equals("T")) {
                        taskList.add(ToDo.parseToDoStringArray(parts));
                    }
                } catch (DateTimeParseException e) {
                    displayMessage(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            return;
        }
    }

    public static String taskListToString() {
        String output = "";
        for (Task t : taskList) {
            String taskDesc = t.getDescription();
            Integer isDone = (t.isDone()) ? 1 : 0;

            if (t instanceof Deadline) {
                Deadline task = (Deadline)t;
                output += "D";
                output += "/" + isDone;
                output += "/" + taskDesc;
                output += "/" + task.getByInStorageFormat();
            } else if (t instanceof Event) {
                Event task = (Event) t;
                output += "E";
                output += "/" + isDone;
                output += "/" + taskDesc;
                output += "/" + task.getFromInStorageFormat();
                output += "/" + task.getToInStorageFormat();
            } else if (t instanceof ToDo) {
                output += "T";
                output += "/" + isDone;
                output += "/" + taskDesc;
            }
            output += System.lineSeparator();
        }

        return output;
    }

    public static void displayMessage(String msg) {

        String wrapTop = "__________________________\n";
        String wrapBottom = "\n__________________________";
        System.out.println(wrapTop + msg.toString() + wrapBottom);
    }
}
