import java.util.Scanner;

public class Duke {
    static String line = "    ______________________________________________________________";

    public static void main(String[] args) {
        startDuke();
    }

    public static void startDuke() {
        greeting();
        Scanner sc = new Scanner(System.in);
        boolean active = true;
        List list = new List();
        while (active) {
            String command = sc.next();
            System.out.println(line);
            switch (command) {
                case "bye":
                    end();
                    active = false;
                    break;
                case "list":
                    list.print();
                    break;
                case "mark":
                    int markIdx = sc.nextInt();
                    list.get(markIdx).markDone();
                    break;
                case "unmark":
                    int unmarkIdx = sc.nextInt();
                    list.get(unmarkIdx).unmark();
                    break;
                case "todo":
                    String todoTask = sc.nextLine();
                    list.add(todoTask);
                    break;
                case "deadline":
                    String temp = sc.nextLine();
                    String[] dlTask = temp.split("/by");
                    list.add(dlTask[0], dlTask[1]);
                    break;
                case "event":
                    String temp2 = sc.nextLine();
                    String[] eventTask = temp2.split("/from|/to");
                    list.add(eventTask[0], eventTask[1], eventTask[2]);
                    break;
            }
            System.out.println(line + "\n");
        }
        sc.close();
    }

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void end() {
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
