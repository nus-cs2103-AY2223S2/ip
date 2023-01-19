import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();

    private static void PixlPrint(String text) {
        System.out.println(Values.PURPLE + "PixlBot: " + Values.RESET + text);
//        System.out.println("Pixlbot: " + text);
        System.out.println(Values.HLINE);
    }

    private static void PixlPrint(String text, String textColor) {
        System.out.println(Values.PURPLE + "PixlBot: " + textColor + text + Values.RESET);
        System.out.println(Values.HLINE);
    }

    private static String formatTask(Task task) {
        return String.format("[%s][%s] %s", task.getTaskType(), task.getStatusIcon(), task.getDescription());
    }

    private static int indexOf(String[] arr, String item) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return 0;
    }

    private static void listCommand() {
        String output = "List:\n";
        for (int i = 0; i < list.size(); i++) {
            output += "\t" + (i+1) + ". " + formatTask(list.get(i)) + "\n";
        }
        PixlPrint(output);
    }

    private static void todoCommand(String command) throws DukeException{
        String[] parts = command.split("\\s+");
        if (parts.length == 1) {
            throw new DukeException("ToDo description cannot be empty.");
        }

        // Get task name.
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            taskName.append(i == 1 ? "" : Values.SPACE);
            taskName.append(parts[i]);
        }

        Task task = new ToDo(taskName.toString());
        list.add(task);
        PixlPrint("Added new todo!\n" +
                "\t" + formatTask(task));
    }

    private static void deadlineCommand(String command) throws DukeException{
        String[] parts = command.split("\\s+");
        int byIndex = indexOf(parts, "/by");

        // Get name of task.
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < byIndex; i++) {
            taskName.append(i == 1 ? "" : Values.SPACE);
            taskName.append(parts[i]);
        }
        // Get due date.
        StringBuilder dueDate = new StringBuilder();
        for (int i = byIndex+1; i < parts.length; i++) {
            dueDate.append(i == byIndex+1 ? "" : Values.SPACE);
            dueDate.append(parts[i]);
        }

        if (taskName.length() == 0 || dueDate.length() == 0) {
            throw new DukeException("Please provide both a deadline description and a due date.\n" +
                    "Format: deadline <description> /by <due_date>");
        }

        Task task = new Deadline(taskName.toString(), dueDate.toString());
        list.add(task);
        PixlPrint("Added new deadline!\n" +
                "\t" + formatTask(task));
    }

    private static void eventCommand(String command) throws DukeException {
        String[] parts = command.split("\\s+");
        int fromIndex = indexOf(parts, "/from");
        int toIndex = indexOf(parts, "/to");

        // Get task name.
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < fromIndex; i++) {
            taskName.append(i == 1 ? "" : Values.SPACE);
            taskName.append(parts[i]);
        }
        // Get start date.
        StringBuilder startDate = new StringBuilder();
        for (int i = fromIndex+1; i < toIndex; i++) {
            startDate.append(i == fromIndex+1 ? "" : Values.SPACE);
            startDate.append(parts[i]);
        }
        // Get end date.
        StringBuilder endDate = new StringBuilder();
        for (int i = toIndex+1; i < parts.length; i++) {
            endDate.append(i == toIndex+1 ? "" : Values.SPACE);
            endDate.append(parts[i]);
        }

        if (taskName.length() == 0 || startDate.length() == 0 || endDate.length() == 0) {
            throw new DukeException("Please provide a description, start date, and end date.\n" +
                    "Format: event <description> /from <start_date> /to <end_date>");
        }

        Task task = new Event(taskName.toString(), startDate.toString(), endDate.toString());
        list.add(task);
        PixlPrint("Added new event!\n" +
                "\t" + formatTask(task));
    }

    private static void markCommand(String command) throws DukeException{
        try {
            Task task = list.get(Integer.parseInt(command.split("\\s+")[1]) - 1);
            task.complete();
            PixlPrint("You completed a task!\n" +
                    "\t" + formatTask(task));
        } catch (Exception e) {
            throw new DukeException("Please provide a valid task number to mark.");
        }
    }

    private static void unmarkCommand(String command) throws DukeException {
        try {
            Task task = list.get(Integer.parseInt(command.split("\\s+")[1]) - 1);
            task.uncomplete();
            PixlPrint("Un-doing the task...\n" +
                    "\t" + formatTask(task));
        } catch (Exception e) {
            throw new DukeException("Please provide a valie task number to unmark.");
        }
    }

    private static void chatCycle(String command) throws DukeException{
        if (command.startsWith("list")) {
            listCommand();
        } else if (command.startsWith("mark")) {
            markCommand(command);
        } else if (command.startsWith("unmark")) {
            unmarkCommand(command);
        } else if (command.startsWith("todo")) {
            todoCommand(command);
        } else if (command.startsWith("deadline")) {
            deadlineCommand(command);
        } else if (command.startsWith("event")) {
            eventCommand(command);
        } else {
            throw new DukeException("I don't know that command.");
        }
    }

    public static void main(String[] args) {
        // Print the logo and welcome user.
        System.out.println("Welcome to\n" + Values.LOGO);
        System.out.println("Enter a command to start.\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("You: ");
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            try {
                chatCycle(command);
            } catch (Exception e) {
                PixlPrint("Uh oh! " + e.getMessage(), Values.RED);
            }

            System.out.print("You: ");
            command = scanner.nextLine();
        }

        // End program
        PixlPrint("Goodbye! See you again :)");
        System.out.println(Values.HLINE);
    }
}
