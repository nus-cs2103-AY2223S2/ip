import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskStore = new ArrayList<>(100);
    private static String endCommand = "bye";
    private static String listCommand = "list";
    private static String mark = "mark";
    private static String unmark = "unmark";
    private static String byIndicator = "/by";
    private static String fromIndicator = "/from";
    private static String toIndicator = "/to";
    private static String deadlineCommand = "deadline";
    private static String todoCommand = "todo";
    private static String eventCommand = "event";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        String separator = "____________________________________________________________\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        System.out.println(greeting + separator);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals(Duke.endCommand)) {
                sc.close();
                System.out.println(goodbye + separator);
                break;
            } else if (command.equals(Duke.listCommand)) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Duke.taskStore.size(); i++) {
                    System.out.println((i + 1) + ". " + Duke.taskStore.get(i));
                }
            } else {
                processCommand(command);
            }
            System.out.println(separator);
        }
    }

    private static void processCommand(String line) {
        String[] splitCommand = line.split(" ");
        String command = splitCommand[0];
        if (command.equals(Duke.mark)) {
            int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            Duke.taskStore.get(taskIndex).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskStore.get(taskIndex));
        } else if (command.equals(Duke.unmark)) {
            int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            Duke.taskStore.get(taskIndex).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskStore.get(taskIndex));
        } else {
            Task task = null;
            if (command.equals(Duke.todoCommand)) {
                task = new Todo(line.split(Duke.todoCommand)[1].trim());
            } else if (command.equals(Duke.deadlineCommand)) {
                String details = line.split(Duke.deadlineCommand)[1].trim();
                String name = details.split(Duke.byIndicator)[0].trim();
                String deadline = details.split(Duke.byIndicator)[1].trim();
                task = new Deadline(name, deadline);
            } else if (command.equals(Duke.eventCommand)) {
                String details = line.split(Duke.eventCommand)[1].trim();
                String name = details.split(Duke.fromIndicator)[0].trim();
                String from = details.split(Duke.fromIndicator)[1].split(Duke.toIndicator)[0].trim();
                String to = details.split(Duke.fromIndicator)[1].split(Duke.toIndicator)[1].trim();
                task = new Event(name, from, to);
            }
            Duke.taskStore.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have " + taskStore.size() + " tasks in the list.");
        }
    }

}
