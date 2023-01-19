import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printText("\t Hello! I'm Duke\n\t What can I do for you?");

        TaskList list = new TaskList();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        while (!input.equals("bye")) {
            try {
                switch (input) {
                    case "list":
                        printText(list.list());
                        break;
                    case "mark":
                        printText(list.mark(scanner.nextLine().strip()));
                        break;
                    case "unmark":
                        printText(list.unMark(scanner.nextLine().strip()));
                    case "delete":
                        printText(list.delete(scanner.nextLine().strip()));
                        break;
                    case "todo":
                        printText(list.add(TaskType.ToDos, scanner.nextLine().strip()));
                        break;
                    case "deadline":
                        printText(list.add(TaskType.Deadlines, scanner.nextLine().strip()));
                        break;
                    case "event":
                        printText(list.add(TaskType.Events, scanner.nextLine().strip()));
                        break;
                    default:
                        throw new DukeUnknownCommandException();
                }
            } catch (DukeException dukeException){
                printText(dukeException.getMessage());
            }

            input = scanner.next();
        }

        printText("\t Bye. Hope to see you again soon!");
    }

    public static void printText(String s) {
        System.out.println("\t____________________________________________________________");
        System.out.println(s);
        System.out.println("\t____________________________________________________________\n");
    }
}

