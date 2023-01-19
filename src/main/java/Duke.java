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
            divider();
            
            if (task.equalsIgnoreCase("list")) {
                tasks.printList();
            }
            else if (task.split(" ")[0].equalsIgnoreCase("todo")) {
                String desc = task.split(" ")[1];
                tasks.addToList(new Todo(desc));

            } else if (task.split(" ")[0].equalsIgnoreCase("deadline")) {
                String temp = task.split(" ", 2)[1]; //task and deadline remain
                String[] details = temp.split("/by ");
                tasks.addToList(new Deadline(details[0], details[1]));

            } else if (task.split(" ")[0].equalsIgnoreCase("event")) {
                String temp = task.split(" ", 2)[1]; //task, from and to remain
                String[] details = temp.split("/from ", 2);
                String[] time = details[1].split("/to ");
                tasks.addToList(new Event(details[0], time[0], time[1]));

            } else if (task.split(" ")[0].equalsIgnoreCase("mark")) {
                int taskNum = Integer.parseInt(task.split(" ")[1]) - 1;
                tasks.markTaskDone(taskNum);
            }
            else if (task.split(" ")[0].equalsIgnoreCase("unmark")) {
                int taskNum = Integer.parseInt(task.split(" ")[1]) - 1;
                tasks.markTaskUndone(taskNum);
            }

            divider();
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
