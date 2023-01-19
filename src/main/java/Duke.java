import java.util.Scanner;
import DukeExceptions.*;
public class Duke {
    public static void printResponse(String input) {
        String horizLine = "_____________________________";
        System.out.printf("%s\n%s\n%s\n%n", horizLine, input, horizLine);
    }

    public static void main(String[] args) {
        ToDoList lst = new ToDoList();
        printResponse("Hello! I'm Interrobang\nWhat can I do for you today?");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();

        while (!command.equals("bye")) {
            try {
                switch (command) {
                    case "list":
                        printResponse(lst.listItems());
                        break;
                    case "mark":
                    case "unmark":
                        printResponse(lst.changeState(scanner.nextLine(), command));
                        break;
                    case "todo":
                        printResponse(lst.add(Todo.createTodo(scanner.nextLine())));
                        break;
                    case "deadline":
                        printResponse(lst.add(Deadline.createDeadline(scanner.nextLine())));
                        break;
                    case "event":
                        printResponse(lst.add(Event.createEvent(scanner.nextLine())));
                        break;
                    default:
                        throw new DukeUnknownInputException();
                }
            } catch (DukeException e) {
                printResponse(e.toString());
            }
            command = scanner.next();
        }
        printResponse("Bye! Hope to see you again soon!");
    }
}
