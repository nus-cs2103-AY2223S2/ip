import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello, I am Duke. \nWhat can I do for you?");

        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + ". " + tasks.get(i));
                }
            } else if (input.startsWith("mark ")) {
                String taskName = input.substring(5);
                int index = Integer.parseInt((taskName)) - 1;
                Task task = tasks.get(index);
                task.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
            } else if (input.startsWith("unmark ")) {
                String taskName = input.substring(7);
                int index = Integer.parseInt((taskName)) - 1;
                Task task = tasks.get(index);
                task.unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);
            } else {
                Task task = new Task(input);
                tasks.add(task);
                System.out.println("added: " + task);
            }
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

        scanner.close();
    }
}

class Task {
    private String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public String getName() {
        return taskName;
    }

    public boolean isDone() {
        return done;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
