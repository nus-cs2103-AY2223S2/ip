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
            } else {
                System.out.println("\t" + line);
                ToDoList.add(line);
            }
            System.out.println("\t--------------------------");
        }
    }
}
