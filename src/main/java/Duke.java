import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hi! I'm Samantha\nHow can I help?");

        // take in input command from user
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // initialise array of Task objects and task counter
        ArrayList<Task> tasks = new ArrayList<Task>();
        //Task[] tasks = new Task[100];
        int taskCounter = 0;

        // loop while user has not entered 'bye' command
        while (!s.equals("bye")) {
            try{
                // method handleCommand handles current command, returns updated taskCounter variable
                taskCounter = handleCommand(s, tasks, taskCounter);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // take in next command
            s = sc.nextLine();
        }

        // exit
        System.out.println("    Bye. Hope to see you soon!");
    }

    // handleCommand takes in command String s, current tasks, and current number of tasks
    // updates task array and returns taskCounter
    public static int handleCommand(String s, ArrayList<Task> tasks, int taskCounter) {
        // user enters list command
        if (s.contains("list")) {
            if (tasks.isEmpty()) {
                System.out.println("    You have no tasks");
            } else {
                for (int i = 0; i < taskCounter; i++) {
                    Task task = tasks.get(i);
                    System.out.println("    " + (i + 1) + ". " + task);
                }
            }

            // user enters mark or unmark command
        } else if (s.contains("mark") || s.contains("unmark")) {
            int taskNumber = Integer.parseInt(s.substring(s.length() - 1)) - 1;
            tasks.get(taskNumber).toggleMarked();
            if (s.contains("unmark")) {
                System.out.println("    OK, I've marked this task as not done yet:");
            } else {
                System.out.println("    Nice! I've marked this task as done:");
            }
            System.out.println("  " + tasks.get(taskNumber).toString());

            // user enters a new task
        } else if (s.contains("todo")) {
            if (s.substring(4).isBlank()) {
                System.out.println("    OOPS!!! The description of a todo cannot be empty.");
            } else {
                Task newTask = new Todo(s.substring(5));
                tasks.add(newTask);
                System.out.println("    added: " + newTask);
                return taskCounter + 1;
            }

        } else if (s.contains("deadline")) {
            if (s.substring(8).isBlank()) {
                System.out.println("    OOPS!!! The description of a deadline cannot be empty.");
            } else {
                String by = s.substring(s.indexOf("/") + 4);
                Task newTask = new Deadline(s.substring(9, s.indexOf("/") - 1), by);
                tasks.add(newTask);
                System.out.println("    added: " + newTask);
                return taskCounter + 1;
            }

        } else if (s.contains("event")) {
            if (s.substring(5).isBlank()) {
                System.out.println("    OOPS!!! The description of a event cannot be empty.");
            } else {
                String from = s.substring(s.indexOf("/") + 6, s.lastIndexOf("/"));
                String to = s.substring(s.lastIndexOf("/") + 4);
                Task newTask = new Event(s.substring(6, s.indexOf("/") - 1), from, to);
                tasks.add(newTask);
                System.out.println("    added: " + newTask);
                return taskCounter + 1;
            }

        } else if (s.contains("delete")) {
            if (s.substring(6).isBlank()) {
                System.out.println("    OOPS!!! You have not entered anything to delete.");
            } else {
                int taskNumber = Integer.parseInt(s.substring(s.length() - 1)) - 1;
                System.out.println("    Noted. I've removed this task:\n      " + tasks.get(taskNumber) +
                        "\n    Now you have " + (taskCounter - 1) + " tasks in the list");
                tasks.remove(taskNumber);
                return taskCounter - 1;
            }
        } else {
            //throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
            return taskCounter;
        }
        return taskCounter;
    }

}

// custom Task class to store individual tasks that the user enters
class Task {
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
    
class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {

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

class Event extends Task {
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

class DukeException extends Exception {
    public DukeException(String message) {

        super(message);
    }
}