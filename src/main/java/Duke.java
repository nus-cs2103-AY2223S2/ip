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
        while (true) {
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
