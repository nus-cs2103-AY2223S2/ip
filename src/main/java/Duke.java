import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        Tasks tasks = new Tasks();
        Scanner myScan = new Scanner(System.in);
        String task = myScan.nextLine();

        while (!(task.equalsIgnoreCase("bye"))) {
            if (task.equalsIgnoreCase("list")) {
                divider();
                tasks.printList();
                divider();
            }

            else if (task.split(" ")[0].equalsIgnoreCase("mark")) {
                int taskNum = Integer.parseInt(task.split(" ")[1]) - 1;
                divider();
                tasks.markTaskDone(taskNum);
                divider();
            }

            else if (task.split(" ")[0].equalsIgnoreCase("unmark")) {
                int taskNum = Integer.parseInt(task.split(" ")[1]) - 1;
                divider();
                tasks.markTaskUndone(taskNum);
                divider();
            }

            else {
                divider();
                tasks.addToList(new Task(task));
                divider();
            }
            task = myScan.nextLine();
        }

        exit();
    }

    public static void greet() {
        System.out.println("Hi there!\nWhat can I do for you on this fine day :)?");
        divider();
    }

    public static void exit() {
        System.out.println("YAY Thank GOD! BYEEEEE~");
    }

    public static void divider() {
        System.out.println("-".repeat(50));
    }
}
