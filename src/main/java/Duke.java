import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static Task[] li = new Task[100];

    public static void addTask(String task) {
        int i = Task.getCount();
        li[i] = new Task(task);
        System.out.println("new task added: " + task);
    }

    public static void listCommand() {
        if (Task.getCount() == 0) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.getCount(); i++) {
                System.out.println(i+1+ "." + li[i]);

            }
        }
        System.out.println("What else can I do for you?");
    }

    public static void blahCommand() {
        System.out.println("blah");
        System.out.println("What else can I do for you?");
    }

    public static void byeCommand() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    public static void checkMarkOrAdd(String str) {
        if (str.equals("mark") || str.equals("unmark") || str.equals("mark ") || str.equals("unmark ")) {
            System.out.println("You need to specify the task number " +
                    "that you would like me to " + str + " after the command.");
            System.out.println("e.g " + str + " 2");
        } else {
            String arr[] = str.split("\\s+");
            if (arr.length != 2 ||
                    (arr.length == 2 && !arr[0].equals("mark")) && !isNumber(arr[1]) ||
                    (arr.length == 2 && !arr[0].equals("unmark")) && !isNumber(arr[1])) {
                addTask(str);
            } else {
                if (arr.length == 2 && (arr[0].equals("mark")
                        && Integer.parseInt(arr[1]) <= Task.getCount() && Integer.parseInt(arr[1]) != 0)) {
                    // mark task
                    Task t = li[Integer.parseInt(arr[1]) - 1];
                    t.mark();
                } else if (arr.length == 2 && (arr[0].equals("unmark")
                        && Integer.parseInt(arr[1]) <= Task.getCount() && Integer.parseInt(arr[1]) != 0)) {
                    // unmark task
                    Task t = li[Integer.parseInt(arr[1])-1];
                    t.unMark();

                } else if (arr.length == 2 && arr[0].equals("unmark")
                        && Integer.parseInt(arr[1]) > Task.getCount() || Integer.parseInt(arr[1]) == 0) {
                    System.out.println("Huh.. the task does not exist.");

                } else if (arr.length == 2 && arr[0].equals("mark")
                        && Integer.parseInt(arr[1]) > Task.getCount() || Integer.parseInt(arr[1]) == 0) {
                    System.out.println("Huh.. the task does not exist.");
                }
            }
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                listCommand();
                command = sc.nextLine();
            } else if (command.equals("blah")) {
                blahCommand();
                command = sc.nextLine();
            } else {
                checkMarkOrAdd(command);
                System.out.println("What else can I do for you?");
                command = sc.nextLine();
            }
        }
        byeCommand();

    }
}
