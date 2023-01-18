import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hiii! I'm panpan\nWhat's up?");
        //echo();
        add();
    }
    public static void echo() {
        Scanner input = new Scanner(System.in);
        String cmd;
        while (true) {
            cmd = input.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("Buhbyeee, hope to see you again soon <3");
                return;
            } else {
                System.out.println(cmd);
            }
        }
    }

    public static void add() {
        Scanner input = new Scanner(System.in);
        String cmd;
        Task[] list = new Task[100];
        Integer count = 0;
        Integer num;
        while (true) {
            cmd = input.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("Buhbyeee, hope to see you again soon <3");
                return;
            } else if (cmd.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= count; i++) {
                    System.out.println(i + "." + list[i - 1].toString());
                }
            } else if (cmd.startsWith("mark")) {
                num = Integer.valueOf(cmd.substring(5));
                list[num - 1].setDone();
                System.out.println("Nice! I've marked this task as done:\n  " + list[num - 1].toString());
            } else if (cmd.startsWith("unmark")) {
                num = Integer.valueOf(cmd.substring(7));
                list[num - 1].setUndone();
                System.out.println("OK, I've marked this task as not done yet:\n  " + list[num - 1].toString());
            } else {
                System.out.println("Got it. I've added this task:");
                if (cmd.startsWith("todo")) {
                    list[count] = new ToDo(cmd.substring(5));
                } else if (cmd.startsWith("deadline")) {
                    int ind = cmd.indexOf("/by");
                    list[count] = new Deadline(cmd.substring(9, ind - 1), cmd.substring(ind + 4));
                } else if (cmd.startsWith("event")) {
                    int start = cmd.indexOf("/from");
                    int end = cmd.indexOf("/to");
                    list[count] = new Event(cmd.substring(6, start - 1), cmd.substring(start + 6, end - 1), cmd.substring(end + 4));
                } else {
                    list[count] = new Task(cmd);
                }
                System.out.println("  " + list[count].toString());
                count ++;
                System.out.println("Now you have " + count + " tasks in the list.");
            }
        }
    }
}
