import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();
    private void Input() {
        while (true) {
            String userInput = this.scanner.nextLine();
            if (userInput.equals("bye")) {
                this.exit();
                break;
            }
            if (userInput.equals("list")) {
                this.showList();
                continue;
            }
            if (userInput.startsWith("mark")) {
                int taskNum = Integer.parseInt(userInput.substring(5));
                this.markTask(taskNum);
                continue;
            }
            if (userInput.contains("unmark")) {
                int taskNum = Integer.parseInt(userInput.substring(7));
                this.unmarkTask(taskNum);
                continue;
            }
            this.addTask(userInput);
        }
    }
    private void addTask(String userInput) {
        this.list.add(new Task(userInput));
        System.out.println("\tadded: " + userInput + "\n");
    }


    private void exit() {
        System.out.println("\tBye, hope to see you again!");
    }


    private void markTask(int taskNum) {
        this.list.get(taskNum - 1).markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + this.list.get(taskNum - 1) + "\n");
    }

    private void unmarkTask(int taskNum) {
        this.list.get(taskNum - 1).markAsUndone();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + this.list.get(taskNum - 1) + "\n");
    }


    private void showList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println("\t" + i + ". " + this.list.get(i - 1));
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?\n");
        duke.Input();
    }
}