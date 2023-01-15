import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t--------------------------");
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t--------------------------");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String line = sc.nextLine();
            System.out.println("\t--------------------------");
            if (line.equals("bye")) {
                System.out.println("\tBYE!");
                break;
            } else if (line.equals("list")) {
                ToDoList.list();
            } else if (line.matches("mark(.*)")) {
                try {
                    ToDoList.markDone(Integer.parseInt(line.substring(line.length() - 1)));
                } catch (NumberFormatException e) {
                    System.out.println("Please indicate which task to mark as done.");
                }
            } else if (line.matches("unmark(.*)")) {
                try {
                    ToDoList.unmark(Integer.parseInt(line.substring(line.length() - 1)));
                } catch (NumberFormatException e) {
                    System.out.println("Please indicate which task to unmark.");
                }
            } else {
                System.out.println("\t" + line);
                ToDoList.add(line);
            }
            System.out.println("\t--------------------------");
        }
    }
}
