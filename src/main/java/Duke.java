import java.util.Scanner;
public class Duke {
    public static void printResponse(String input) {
        String horizLine = "_____________________________";
        System.out.println(horizLine);
        System.out.println(input);
        System.out.println(horizLine);
        System.out.println();
    }
    public static void main(String[] args) {
        printResponse("Hello! I'm Interrobang\nWhat can I do for you today?");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            printResponse(command);
            command = scanner.nextLine();
        }
        printResponse("Bye! Hope to see you again soon!");
    }
}
