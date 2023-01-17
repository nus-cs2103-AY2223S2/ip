import com.sun.source.tree.WhileLoopTree;

import java.util.Scanner;

public class Duke {
    private static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n" + "\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }
    private static void echo(String s) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + s);
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
            echo(input);
            input = scanner.nextLine();
        }

        exit();
    }
}
