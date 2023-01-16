import java.util.Scanner;

public class Duke {

    private Task[] tasks;
    private int cnt;

    private class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return isDone ? "X" : " ";
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    public Duke() {
        this.tasks = new Task[100];
        this.cnt = 0;
    }

    private static void space() {
        System.out.print("     ");
    }

    private static void line() {
        space();
        System.out.println("--------------------------------------------------");
    }

    private static void greeting() {
        line();
        space();
        System.out.println("Hello! I'm WindyCall");
        space();
        System.out.println("How can I help you?");
        line();
    }

    private static void byeWords() {
        line();
        space();
        System.out.println("Bye. Always willing to provide my help for you!!!");
        line();
    }

    private static void echoCommand(String message) {
        line();
        space();
        System.out.println(message);
        line();
    }

    private void addCommand(String message) {
        line();
        space();
        System.out.println("added: " + message);
        Task newTask = new Task(message);
        this.tasks[cnt] = newTask;
        this.cnt++;
        line();
    }

    private void displayLists() {
        line();
        space();
        System.out.println("Here are all of your tasks");
        for (int i = 0; i < this.cnt; i++) {
            space();
            System.out.println((i + 1) + "." + this.tasks[i]);
        }
        line();
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        greeting();
        Duke chatBox = new Duke();
        while(true) {
            String userCommand = scan.nextLine();
            if (userCommand.equals("bye")) {
                byeWords();
                break;
            }
            if (userCommand.equals("list")) chatBox.displayLists();
            else chatBox.addCommand(userCommand);
        }
        return ;
    }
}
