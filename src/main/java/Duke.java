import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        try {
            TaskList list = new TaskList();
            printText("\t Hello! I'm Duke\n\t What can I do for you?");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            while (!input.equals("bye")) {
                try {
                    printText(executeCommand(list, input, scanner.nextLine()));

                } catch (DukeException dukeException) {
                    printText(dukeException.getMessage());
                }
                input = scanner.next();
            }
            list.writeToFile();
        } catch (DukeException dukeException){
            printText(dukeException.getMessage());
        }
        printText("\t Bye. Hope to see you again soon!");
    }

    private static void printText(String s) {
        System.out.println("\t____________________________________________________________");
        System.out.println(s);
        System.out.println("\t____________________________________________________________\n");
    }

    private static String executeCommand(TaskList list, String fn, String info) throws DukeException {
        switch(fn) {
            case "list":
                return list.list();
            case "mark":
                return list.mark(info.strip());
            case "unmark":
                return list.unMark(info.strip());
            case "delete":
                return list.delete(info.strip());
            case "todo":
                return list.add(TaskType.ToDos, info.strip());
            case "deadline":
                return list.add(TaskType.Deadlines, info.strip());
            case "event":
                return list.add(TaskType.Events, info.strip());
            default:
                throw new DukeUnknownCommandException();
        }
    }
}

