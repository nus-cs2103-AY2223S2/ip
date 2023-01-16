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

        public void markAsDone() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
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
        space();
        System.out.println(message);
    }

    private void addTask(String message) {
        space();
        System.out.println("added: " + message);
        Task newTask = new Task(message);
        this.tasks[cnt] = newTask;
        this.cnt++;
    }

    private void displayTasks() {
        space();
        System.out.println("Here are all of your tasks:");
        for (int i = 0; i < this.cnt; i++) {
            space();
            System.out.println((i + 1) + "." + this.tasks[i]);
        }
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
            line();
            if (userCommand.equals("list")) chatBox.displayTasks();
            else {
                String[] parts = userCommand.split(" ");
                if (parts[0].equals("mark")) {
                    System.out.println("     Good job! I've marked this task as done:");
                    int num = Integer.parseInt(parts[1]);
                    chatBox.tasks[num - 1].markAsDone();
                    space();
                    System.out.println(chatBox.tasks[num - 1]);
                } else if (parts[0].equals("unmark")) {
                    System.out.println("     OK, I've marked this task as not done yet:");
                    int num = Integer.parseInt(parts[1]);
                    chatBox.tasks[num - 1].unmark();
                    space();
                    System.out.println(chatBox.tasks[num - 1]);
                } else chatBox.addTask(userCommand);
            }
            line();
        }
        return ;
    }
}
