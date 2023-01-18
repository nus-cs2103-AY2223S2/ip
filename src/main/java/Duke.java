import java.util.Scanner;

public class Duke {
    private static final String Indentation = " ";
    private static final String Horizontal = "____________________________________________________________";
    private static int count = 0;
    private static Task[] listname;
    public static void main(String[] args) {
        //show logo
        logo();
        //greeting
        greet();
        //input command
        Scanner str = new Scanner(System.in);
        //no more than 100 tasks
        listname = new Task[100];
        String command;
        String[] words;


        do {
            command = str.nextLine();
            words = command.split(" ");

            //if command is equal to bye, exit()
            //if command is not equal to bye, distinguish list or normal command
            if (!command.equals("bye")) {
                if (command.equals("list")) {
                    list();
                } else if (words[0].equals("mark")) {
                    done(words[1]);
                } else if (words[0].equals("unmark")) {
                    undone(words[1]);
                }else {
                    listname[count] = new Task(command);
                    count++;
                    System.out.println(Indentation + Horizontal);
                    System.out.println(Indentation + "Added: " + command);
                    System.out.println(Indentation + Horizontal);
                }
            }

        } while (!command.equals("bye"));

        exit();

    }

    public static void logo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void greet() {
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Hello! I'm Duke");
        System.out.println(Indentation + "What can I do for you?");
        System.out.println(Indentation + Horizontal);
    }

    public static void exit() {
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Bye. Hope to see you again soon!");
        System.out.println(Indentation + Horizontal);
    }

    public static void list() {
        System.out.println(Indentation + Horizontal);
        System.out.println(Indentation + "Here are the tasks in your list:");

        for (int i = 0; i < count; i++) {
            System.out.println(Indentation + (i + 1) + "." + Indentation +
                    "[" + listname[i].getStatusIcon() + "] " + listname[i].description);
        }

        System.out.println(Indentation + Horizontal);
    }

    public static void done (String num) {
        int number = Integer.parseInt(num) - 1;
        listname[number].isDone = true;

        //System.out.println(number);
        System.out.println(Indentation + Horizontal);
        System.out.println("Nice! I've marked this task as done:");

        System.out.println(Indentation +
                    "[" + listname[number].getStatusIcon() + "] " + listname[number].description);

        System.out.println(Indentation + Horizontal);
    }

    public static void undone(String num) {
        int number = Integer.parseInt(num) - 1;
        listname[number].isDone = false;

        //System.out.println(number);
        System.out.println(Indentation + Horizontal);
        System.out.println("OK, I've marked this task as not done yet:");

        System.out.println(Indentation +
                "[" + listname[number].getStatusIcon() + "] " + listname[number].description);

        System.out.println(Indentation + Horizontal);
    }
}
