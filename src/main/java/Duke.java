import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String reply = "";
        TodoList todoList = new TodoList();

        printTextWithLines("Hello! I'm Duke\nWhat can I do for you?");

        while (true) {
            reply = input.nextLine();
            if (reply.equals("bye")) {
                printTextWithLines("Bye. Hope to see you again soon!");
                break;
            } else if (reply.equals("list")) {
                printTextWithLines(todoList.toString());
            } else {
                todoList.addTask(reply);
                printTextWithLines("added: " + reply);
            }
        }

    }

    static void printTextWithLines(String text) {
        printLineBreak();
        System.out.println(text);
        printLineBreak();
        System.out.println();
    }
    static void printLineBreak() {
        System.out.println("____________________________________________________________");
    }
}
