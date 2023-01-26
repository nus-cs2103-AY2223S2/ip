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
            if (userInput.startsWith("unmark")) {
                int taskNum = Integer.parseInt(userInput.substring(7));
                this.unmarkTask(taskNum);
                continue;
            }
            if (userInput.startsWith("todo")) {
                String todo = userInput.replace("todo ", "");
                addTodo(todo);
                continue;
            }
            if (userInput.startsWith("event")) {
                String[] event = userInput.replace("event ", "").split(" /from ");
                addEvent(event[0], event[1]);
                continue;
            }
            if (userInput.startsWith("deadline")) {
                String[] deadline = userInput.replace("deadline ", "").split(" /by ");
                addDeadline(deadline[0], deadline[1]);
                continue;
            }
            this.addTask(userInput);
        }
    }

    private void addTask(String userInput) {
        this.list.add(new Task(userInput));
        System.out.println("\tadded: " + userInput + "\n");
    }

    private void addTodo(String description) {
        Todo newTodo = new Todo(description);
        list.add(newTodo);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newTodo);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }

    private void addDeadline(String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        list.add(newDeadline);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newDeadline);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }

    private void addEvent(String description, String from) {
        Event newEvent = new Event(description, from);
        list.add(newEvent);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newEvent);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
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

    private void exit() {
        System.out.println("\tBye, hope to see you again!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?\n");
        duke.Input();
    }
}