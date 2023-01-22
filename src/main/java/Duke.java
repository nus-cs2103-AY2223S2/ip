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
                    try {
                        list.add(todoTask);
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                    break;
                case "deadline":
                    String temp = sc.nextLine();
                    if (!temp.matches("^.+(\\s)/by(\\s).+$")) {
                        DukeException msgMismatch = new DukeException(
                                "Please use the correct format to add a deadline.");
                        System.out.println(msgMismatch);
                        break;
                    }
                    String[] dlTask = temp.split("/by");
                    try {
                        list.add(dlTask[0], dlTask[1]);
                    } catch (DukeException e) {
                        System.out.println(e);
                        break;
                    }
                    break;
                case "event":
                    String temp2 = sc.nextLine();
                    if (!temp2.matches("^.+(\\s)/from(\\s).+(\\s)/to.*$")) {
                        DukeException msgMismatch = new DukeException("Please use the correct format to add an event.");
                        System.out.println(msgMismatch);
                        break;
                    }
                    String[] eventTask = temp2.split("/from|/to");
                    try {
                        list.add(eventTask[0], eventTask[1], eventTask[2]);
                    } catch (DukeException e) {
                        System.out.println(e);
                        break;
                    }
                    break;
                case "delete":
                    int deleteIdx = sc.nextInt();
                    list.remove(deleteIdx);
                    break;
                default:
                    DukeException unknowCmd = new DukeException("I'm sorry, but I don't know what that means :-(");
                    System.out.println(unknowCmd);
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
