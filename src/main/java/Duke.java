import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TodoList todoList = new TodoList();
        String reply = "";

        printTextWithLines("Hello! I'm Duke\nWhat can I do for you?");

        while (true) {
            reply = input.nextLine();
            if (reply.equals("bye")) {
                printTextWithLines("Bye. Hope to see you again soon!");
                break;
            } else if (reply.equals("list")) {
                printTextWithLines(todoList.toString());
            } else if (reply.startsWith("mark")) {
                int taskNumber = Integer.parseInt(reply.split(" ")[1]);
                todoList.setDone(taskNumber, true);
                printTextWithLines("Nice! I've marked this task as done:\n" + todoList.getTask(taskNumber));
            } else if (reply.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(reply.split(" ")[1]);
                todoList.setDone(taskNumber, false);
                printTextWithLines("OK, I've marked this task as not done yet:\n" + todoList.getTask(taskNumber));
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
