import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {


        begin();

        TodoList todoList = new TodoList();
        ChatBot bot = new ChatBot(todoList);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                bot.execute(input);
            } catch (DukeExceptions error) {
                System.out.println(error.getErrorMessage());
            }
            endCommand();
            input = scanner.nextLine(); //ready for next input
        }
        scanner.close();
        bye();
    }

    private static void begin() {
        endCommand();

        System.out.println("Hello from Duke");
        System.out.println("What can I do for you?");

        endCommand();
    }

    private static void endCommand() {
        System.out.println("____________________________________________________________");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        endCommand();
    }
}
