import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printText("\t Hello! I'm Duke\n\t What can I do for you?");

        ToDoList list = new ToDoList();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        while (!input.equals("bye")) {
            try {
                switch (input) {
                    case "list":
                        printText(list.list());
                        break;
                    case "mark": {
                        int num = Integer.parseInt(scanner.nextLine().strip());
                        printText(list.mark(num - 1));
                    }
                        break;
                    case "unmark": {
                        int num = Integer.parseInt(scanner.nextLine().strip());
                        printText(list.unMark(num - 1));
                    }
                    case "delete": {
                        int num = Integer.parseInt(scanner.nextLine().strip());
                        printText(list.delete(num - 1));
                    }
                        break;
                    case "todo":
                        printText(list.add(TaskType.ToDos, scanner.nextLine().strip()));
                        break;
                    case "deadline":
                        printText(list.add(TaskType.Deadlines, scanner.nextLine()));
                        break;
                    case "event":
                        printText(list.add(TaskType.Events, scanner.nextLine()));
                        break;
                    default:
                        throw new DukeException("\t OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException dukeException){
                printText(dukeException.getMessage());
            } catch (NumberFormatException exception) {
                printText("\t OOPS!!! Task number is invalid.");
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

