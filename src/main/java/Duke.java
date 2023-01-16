import java.util.Scanner;

public class Duke {

    private static final String indentation = "    ";
    private static final String horizontalLines = "____________________________________________________________";
    private static final String newLine = indentation + horizontalLines;

    public static void echo(String commands) {
        System.out.println(newLine);
        System.out.println(indentation + commands);
        System.out.println(newLine);
    }

    public static void exit() {
        System.out.println(newLine);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(newLine);
    }

    public static void greet() {
        System.out.println(newLine);
        System.out.println(indentation + "Hello! I'm Duke");
        System.out.println(indentation + "What can I do for you?");
        System.out.println(newLine);
    }


    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }
            echo(command);
        }
        exit();
    }
}
