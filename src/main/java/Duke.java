import com.sun.source.tree.WhileLoopTree;

import java.util.Scanner;

public class Duke {
    private static String[] task = new String[100];
    private static int numOfTask = 0;
    private static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }
    private static void add(String s) {
        task[numOfTask] = s;
        numOfTask++;

        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + s);
        System.out.println("\t____________________________________________________________");
    }

    private static void list() {
        System.out.println("\t____________________________________________________________");
        for(int i = 0; i < numOfTask; i++) {
            int tmp = i + 1;
            String msg = tmp + ". " + task[i];
            System.out.println("\t" + msg);
        }
        System.out.println("\t____________________________________________________________");
    }
    private static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");

    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            if(input.equals("list")) {
                list();
            } else {
                add(input);
            }
            input = scanner.nextLine();
        }

        exit();
    }
}
