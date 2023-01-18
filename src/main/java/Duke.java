import java.util.Scanner;

public class Duke {
    static String[] inputList = new String[100];
    static int index = 0;

    public static void add(String input) {
        inputList[index] = input;
        index++;
    }

    public static void list() {
        System.out.println("\t--------------------------");
        for (int i = 0; i < index; i++) {

            System.out.println("\t" + (i+1) + ". " + inputList[i]);

        }
        System.out.println("\t--------------------------");
    }
    public static void bye() {
        System.out.println("\t--------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t--------------------------");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        String input;
        Scanner scan = new Scanner(System.in);
        input = scan.nextLine();
        while (true) {
            if (input.equals("bye")) {
                Duke.bye();
                break;
            } else if (input.equals("list")) {
                Duke.list();
                input = scan.nextLine();
            } else {
                Duke.add(input);
                System.out.println("\t--------------------------");
                System.out.println("\tadded: " + input);
                System.out.println("\t--------------------------");
                input = scan.nextLine();
            }
        }
    }
}
