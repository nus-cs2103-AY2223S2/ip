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
        String input = scanner.next();

        while (!input.equals("bye")) {
            switch (input) {
                case "list":
                    printText(list.list());
                    break;
                case "mark": {
                    int num = Integer.parseInt(scanner.next());
                    printText(list.mark(num - 1));
                    }
                    break;
                case "unmark": {
                    int num = Integer.parseInt(scanner.next());
                    printText(list.unMark(num - 1));
                    }
                    break;
                default:
                    printText(list.add(input));
                    break;
            }

            input = scanner.next();
        }

        printText("\t Bye. Hope to see you again soon!");
    }
}

