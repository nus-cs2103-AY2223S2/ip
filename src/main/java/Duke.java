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

    public static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {

        protected String from;
        protected String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to " + to + ")";
        }
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static void process_input(String input) {
        String trigger = input.split(" ")[0];
        int tid = 1;
        Task task;
        String content = "", ddl = "", from = "", to = "";
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
            case "deadline":
                try {
                    input = input.split(trigger)[1];
                    print(input.split("/by")[0]);
                    content = input.split("/by")[0].strip();
                    ddl = input.split("/by")[1].strip();
                } catch (IndexOutOfBoundsException e) {
                    print(e.toString());
                    System.exit(1);
                }
                Deadline deadline = new Deadline(content, ddl);
                todos.add(deadline);
                print("Got it. I've added this task:");
                print("\t" + deadline);
                print("Now you have " + todos.size() + " tasks in the list.");
                break;
            case "event":
                try {
                    input = input.split(trigger)[1];
                    content = input.split("/from")[0].strip();
                    from = input.split("/from")[1].split("/to")[0].strip();
                    to = input.split("/from")[1].split("/to")[1].strip();
                } catch (IndexOutOfBoundsException e) {
                    print(e.toString());
                    System.exit(1);
                }
                Event event = new Event(content, from, to);
                todos.add(event);
                print("Got it. I've added this task:");
                print("\t" + event);
                print("Now you have " + todos.size() + " tasks in the list.");
                break;
            case "todo":
                try {
                    input = input.split(trigger)[1];
                } catch (IndexOutOfBoundsException e) {
                    print(e.toString());
                    System.exit(1);
                }
                Todo todo = new Todo(input);
                todos.add(todo);
                print("Got it. I've added this task:");
                print("\t" + todo);
                print("Now you have " + todos.size() + " tasks in the list.");
            default:
                print(trigger + " not found.");
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