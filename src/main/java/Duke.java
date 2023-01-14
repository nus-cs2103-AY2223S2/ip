import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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
        String endCommand = "bye";
        String listCommand = "list";
        String mark = "mark";
        String unmark = "unmark";
        String by = "/by";
        String from = "/from";
        String to = "/to";
        String deadlineCommand = "deadline";
        String todoCommand = "todo";
        String eventCommand = "event";
        ArrayList<Task> taskStore = new ArrayList<>(100);
        System.out.println(greeting + separator);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            String[] splitCommand = command.split(" ");
            if (command.equals(endCommand)) {
                sc.close();
                System.out.println(goodbye + separator);
                break;
            } else if (command.equals(listCommand)) {
                for (int i = 0; i < taskStore.size(); i++) {
                    System.out.println((i + 1) + ". " + taskStore.get(i));
                }
            } else if (splitCommand.length == 2 && splitCommand[0].equals(mark)) {
                int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
                taskStore.get(taskIndex).setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskStore.get(taskIndex));
            } else if (splitCommand.length == 2 && splitCommand[0].equals(unmark)) {
                int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
                taskStore.get(taskIndex).setDone(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(taskStore.get(taskIndex));
            } else if (splitCommand[0].equals(todoCommand)) {
                Task task = new Todo(String.join(" ", Arrays.copyOfRange(splitCommand, 1, splitCommand.length)));
                taskStore.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + taskStore.size() + " tasks in the list.");
            } else if (splitCommand[0].equals(deadlineCommand)) {
                Task task = null;
                for (int i = 1; i < splitCommand.length; i++) {
                    if (splitCommand[i].equals(by)) {
                        if (i + 1 < splitCommand.length) {
                            String name = String.join(" ", Arrays.copyOfRange(splitCommand, 1, i));
                            String deadline = String.join(" ", Arrays.copyOfRange(splitCommand, i + 1, splitCommand.length));
                            task = new Deadline(name, deadline);
                        }
                        break;
                    }
                }
                taskStore.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + taskStore.size() + " tasks in the list.");
            }
            System.out.println(separator);
        }
    }
}
