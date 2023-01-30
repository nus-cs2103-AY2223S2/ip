import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\t  ____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");

        String[] tasks = new String[100];
        int taskIndex = 0;

        Scanner input = new Scanner(System.in);

        while (true) {
            String command = input.nextLine();

            if (command.equals("bye")) {
                System.out.println("\t Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < taskIndex; i++) {
                    System.out.println("\t " + (i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskIndex] = command;
                taskIndex++;
                System.out.println("\t added: " + command);
            }
        }
    }
}

