import java.util.Scanner;

public class WindyCall {

    private final Task[] tasks;
    private int cnt;

    public WindyCall() {
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
        String[] parts = message.split(" ");

        Task newTask;
        if (parts[0].equals("todo")) {
            String description = message.substring(5);
            System.out.println("Got it. I've added this Todo task:");
            newTask = new Todo(description);
        }
        else if (parts[0].equals("deadline")) {
            int idx = message.indexOf("/by");
            String description = message.substring(9, idx - 1);
            System.out.println("Got it. I've added this Deadline task:");
            String deadline = message.substring(idx + 4);
            newTask = new Deadline(description,deadline);
        }
        else {
            int idxFrom = message.indexOf("/from");
            int idxTo = message.indexOf("/to");
            String description = message.substring(6, idxFrom - 1);
            String from = message.substring(idxFrom + 6, idxTo - 1);
            String to = message.substring(idxTo + 4);
            System.out.println("Got it. I've added this Event task:");
            newTask = new Event(description, from, to);
        }
        space();
        System.out.println(newTask);
        this.tasks[cnt] = newTask;
        this.cnt++;
        space();
        System.out.println("Now you have " + cnt + " tasks in the list.");
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
        WindyCall chatBox = new WindyCall();
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
    }
}
