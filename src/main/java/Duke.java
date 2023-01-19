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
                        System.out.println("    " + (i + 1) + ". " + task.getStatusIcon() + " " + task.getDescription());
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
                System.out.println("  " + tasks[taskNumber].getStatusIcon() + " " + tasks[taskNumber].getDescription());

            // user enters a new task
            }  else {
                Task newTask = new Task(s);
                tasks[taskCounter] = newTask;
                taskCounter++;
                System.out.println("    added: " + s);
            }

            // take in next command
            s = sc.nextLine();
        }

        // exit
        System.out.println("    Bye. Hope to see you soon!");
    }
}

// custom Task class to store individual tasks that user enters
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

    public String getDescription() {
        return this.description;
    }

    public void toggleMarked() {
        this.isDone = !this.isDone;
    }
}
    
