import java.util.Scanner;
public class Duke {
    public static void printResponse(String input) {
        String horizLine = "_____________________________";
        System.out.println(String.format("%s\n%s\n%s\n", horizLine, input, horizLine));
    }

    public static void main(String[] args) {
        ToDoList lst = new ToDoList();
        printResponse("Hello! I'm Interrobang\nWhat can I do for you today?");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        command = command.trim();
        while (true) {
            if (command.contains("mark")) {
                boolean isMark = !command.contains("unmark");
                int beginIndex = isMark ? 4 : 6;
                String input = command.substring(beginIndex);
                if (input.isEmpty() || input.trim().isEmpty()) {
                    printResponse("I think you forgot to tell me a number. Try again but with a space and a number");
                } else if (input.trim().contains(" ")) {
                    printResponse("I'm a little confused, could you please specify just one number?");
                } else {
                    int index = Integer.parseInt(input.trim());
                    if (isMark) {
                        printResponse(lst.mark(index));
                    } else {
                        printResponse(lst.unmark(index));
                    }
                }
                command = scanner.nextLine();
                continue;
            }

            switch (command) {
                case "bye":
                    printResponse("Bye! Hope to see you again soon!");
                    return;
                case "list":
                    printResponse(lst.listItems());
                    break;
                default:
                    printResponse(lst.add(command));
                    break;
            }
            command = scanner.nextLine();
        }
    }
}
