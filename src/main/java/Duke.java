import java.util.Scanner;

public class Duke {
    private static void printText(String s) {
        System.out.println("\t____________________________________________________________");
        System.out.println(s);
        System.out.println("\t____________________________________________________________\n");
    }

    public static void main(String[] args) {
        printText("\t Hello! I'm Duke \n\t What can I do for you?");

        ToDoList list = new ToDoList();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printText(list.list());
            } else {
                printText(list.add(input));
            }

            input = scanner.nextLine();
        }

        printText("\t Bye. Hope to see you again soon!");
    }
}

