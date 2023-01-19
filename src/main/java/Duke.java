import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();

    private static void PixlPrint(String text) {
        System.out.println(Values.PURPLE + "PixlBot: " + Values.RESET + text);
        System.out.println(Values.HLINE);
    }

    private static void listCommand() {
        String output = "List:\n";
        for (int i = 0; i < list.size(); i++) {
            output += "\t" + (i + 1) + ". [" + list.get(i).getStatusIcon() + "] " + list.get(i).description + "\n";
        }
        PixlPrint(output);
    }

    private static void addCommand(String taskName) {
        list.add(new Task(taskName));
        PixlPrint("added: " + taskName);
    }

    private static void markCommand(int taskNumber) {
        Task task = list.get(taskNumber - 1);
        task.complete();
        PixlPrint("You completed a task!\n" +
                "\t[" + task.getStatusIcon() + "] " + task.description);
    }

    private static void unmarkCommand(int taskNumber) {
        Task task = list.get(taskNumber - 1);
        task.uncomplete();
        PixlPrint("Un-doing the task...\n"+
                "\t[" + task.getStatusIcon() + "] " + task.description);
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
            } else {
                addCommand(command);
            }

            System.out.print("You: ");
            command = scanner.nextLine();
        }

        // End program
        PixlPrint("Goodbye! See you again :)");
        System.out.println(Values.HLINE);
    }
}
