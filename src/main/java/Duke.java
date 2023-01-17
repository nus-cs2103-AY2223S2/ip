import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static ArrayList<Task> todos = new ArrayList<>();

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsNotDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + this.description;
        }
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static void process_input(String input) {
        String trigger = input.split(" ")[0];
        int tid = 1;
        Task task;
        switch (trigger) {
            case "bye":
                print("Bye. Hope to see you again soon!");
                System.exit(0);
            case "list":
                if (todos.isEmpty()) {
                    print("No items yet.");
                } else {
                    int i = 1;
                    for (Task t : todos) {
                        print(i + "." + t.toString());
                        i++;
                    }
                }
                break;
            case "mark":
                try {
                    tid = Integer.parseInt(input.split(" ")[1]);
                } catch (IndexOutOfBoundsException e) {
                    print(e.toString());
                    System.exit(1);
                }
                task = todos.get(tid - 1);
                task.markAsDone();
                print("Nice! I've marked this task as done:");
                print(task.toString());
                break;
            case "unmark":
                try {
                    tid = Integer.parseInt(input.split(" ")[1]);
                } catch (IndexOutOfBoundsException e) {
                    print(e.toString());
                    System.exit(1);
                }
                task = todos.get(tid - 1);
                task.markAsNotDone();
                print("OK, I've marked this task as not done yet:");
                print(task.toString());
                break;
            default:
                task = new Task(input);
                todos.add(task);
                print("added:" + input);
        }
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        print(greeting);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            process_input(input);
        }
    }
}