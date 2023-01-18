import java.util.Scanner;

public class Duke {
    static String[] inputList = new String[100];
    static Boolean[] doneList = new Boolean[100];
    static int index = 0;

    public static void add(String input) {
        inputList[index] = input;
        doneList[index] = false;
        index++;
    }
    public static void mark(int input) {
        doneList[input - 1] = true;
        System.out.println("\t--------------------------");
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t[X] " + inputList[input - 1]);
        System.out.println("\t--------------------------");
    }

    public static void unmark(int input) {
        doneList[input - 1] = false;
        System.out.println("\t--------------------------");
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t[ ] " + inputList[input - 1]);
        System.out.println("\t--------------------------");
    }

    public static void list() {
        System.out.println("\t--------------------------");
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            if (doneList[i]) {
                System.out.println("\t" + (i+1) + ". [X] " + inputList[i]);
            } else {
                System.out.println("\t" + (i+1) + ". [ ] " + inputList[i]);
            }
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
        String[] splitInput = input.split(" ");
        while (true) {
            if (splitInput[0].equals("bye")) {
                Duke.bye();
                break;
            } else if (splitInput[0].equals("list")) {
                Duke.list();
                input = scan.nextLine();
                splitInput = input.split(" ");
            } else if (splitInput[0].equals("mark")) {
                Duke.mark(Integer.parseInt(splitInput[1]));
                input = scan.nextLine();
                splitInput = input.split(" ");
            } else if (splitInput[0].equals("unmark")) {
                Duke.unmark(Integer.parseInt(splitInput[1]));
                input = scan.nextLine();
                splitInput = input.split(" ");
            } else {
                Duke.add(input);
                System.out.println("\t--------------------------");
                System.out.println("\tadded: " + input);
                System.out.println("\t--------------------------");
                input = scan.nextLine();
                splitInput = input.split(" ");
            }
        }

    }
}
