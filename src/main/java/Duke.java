import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();

    private static void PixlPrint(String text) {
        System.out.println(Values.PURPLE + "PixlBot: " + Values.RESET + text);
        System.out.println(Values.HLINE);
    }

    private static String formatTask(Task task) {
        return String.format("[%s][%s] %s", task.getTaskType(), task.getStatusIcon(), task.getDescription());
    }

    private static void listCommand() {
        String output = "List:\n";
        for (int i = 0; i < list.size(); i++) {
            output += "\t" + (i+1) + ". " + formatTask(list.get(i)) + "\n";
        }
        PixlPrint(output);
    }

    private static void todoCommand(String taskName) {
        Task task = new ToDo(taskName);
        list.add(task);
        PixlPrint("Added new todo!\n" +
                "\t" + formatTask(task));
    }

    private static void deadlineCommand(String taskName, String dueDate) {
        Task task = new Deadline(taskName, dueDate);
        list.add(task);
        PixlPrint("Added new deadline!\n" +
                "\t" + formatTask(task));
    }

    private static void eventCommand(String taskName, String startDate, String endDate) {
        Task task = new Event(taskName, startDate, endDate);
        list.add(task);
        PixlPrint("Added new event!\n" +
                "\t" + formatTask(task));
    }

    private static void markCommand(int taskNumber) {
        Task task = list.get(taskNumber - 1);
        task.complete();
        PixlPrint("You completed a task!\n" +
                "\t" + formatTask(task));
    }

    private static void unmarkCommand(int taskNumber) {
        Task task = list.get(taskNumber - 1);
        task.uncomplete();
        PixlPrint("Un-doing the task...\n"+
                "\t" + formatTask(task));
    }

    public static void main(String[] args) {
        // Print the logo and welcome user.
        System.out.println("Welcome to\n" + Values.LOGO);
        System.out.println("Enter a command to start.\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("You: ");
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                listCommand();
            } else if (command.startsWith("mark")) {
                markCommand(Integer.parseInt(command.split("\\s+")[1]));
            } else if (command.startsWith("unmark")) {
                unmarkCommand(Integer.parseInt(command.split("\\s+")[1]));
            } else if (command.startsWith("todo")) {
                todoCommand(command);
            } else if (command.startsWith("deadline")) {
                String[] parts = command.split("\\s+");
                deadlineCommand(parts[1], parts[2]);
            } else if (command.startsWith("event")) {
                String[] parts = command.split("\\s+");
                eventCommand(parts[1], parts[2], parts[3]);
            }

            System.out.print("You: ");
            command = scanner.nextLine();
        }

        // End program
        PixlPrint("Goodbye! See you again :)");
        System.out.println(Values.HLINE);
    }
}
