import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hi! I'm Samantha\nHow can I help?");

        // take in input command from user
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // initialise array of Task objects and task counter
        Task[] tasks = new Task[100];
        int taskCounter = 0;

        // loop while user has not entered 'bye' command
        while (!s.equals("bye")) {

            // user enters list command
            if (s.equals("list")) {
                if (tasks[0] == null) {
                    System.out.println("    You have no tasks");
                } else {
                    for (int i = 0; i < taskCounter; i++) {
                        Task task = tasks[i];
                        System.out.println("    " + (i + 1) + ". " + task.toString());
                    }
                }

            // user enters mark or unmark command
            } else if (s.contains("mark") || s.contains("unmark")) {
                int taskNumber = Integer.parseInt(s.substring(s.length() - 1)) - 1;
                tasks[taskNumber].toggleMarked();
                if (s.contains("unmark")) {
                    System.out.println("    OK, I've marked this task as not done yet:");
                } else {
                    System.out.println("    Nice! I've marked this task as done:");
                }
                System.out.println("  " + tasks[taskNumber].toString());

            // user enters a new task
            }  else if (s.contains("todo")) {
                Task newTask = new Todo(s);
                tasks[taskCounter] = newTask;
                taskCounter++;
                System.out.println("    added: " + newTask);

            } else if (s.contains("deadline")) {
                String by = s.substring(s.indexOf("/") + 4);
                Task newTask = new Deadline(s.substring(9, s.indexOf("/") - 1), by);
                tasks[taskCounter] = newTask;
                taskCounter++;
                System.out.println("    added: " + newTask);

            } else if (s.contains("event")) {
                String from = s.substring(s.indexOf("/") + 6, s.lastIndexOf("/"));
                String to = s.substring(s.lastIndexOf("/") + 4);
                Task newTask = new Event(s.substring(6, s.indexOf("/") - 1), from, to);
                tasks[taskCounter] = newTask;
                taskCounter++;
                System.out.println("    added: " + newTask);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            // take in next command
            s = sc.nextLine();
        }

        // exit
        System.out.println("    Bye. Hope to see you soon!");
    }
}

// custom Task class to store individual tasks that the user enters
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void toggleMarked() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
    
public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

public class Deadline extends Task {

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

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }

}