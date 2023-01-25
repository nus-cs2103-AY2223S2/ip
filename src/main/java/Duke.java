import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;


public class Duke {
    public static void main(String[] args) {

        System.out.println("    . . . Loading . . . ");

        // initialise array of Task objects and task counter
        ArrayList<Task> tasks = new ArrayList<Task>();

        //Task[] tasks = new Task[100];
        int taskCounter = 0;

        File file = new File("../data/duke.txt");
        try {
            Scanner saveFile = new Scanner(file);
            System.out.println("    Saved data found, welcome back!");
            while (saveFile.hasNextLine()) {
                taskCounter = loadSaved(saveFile.nextLine(), tasks, taskCounter);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("    No saved data not found, will be created");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("File already created");
            }
        }

        System.out.println("    Hi! I'm Samantha\n    How can I help?");


        // take in input command from user
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

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

        try {
            FileWriter fWriter = new FileWriter("../data/duke.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                fWriter.write(t.toSavedString());
                if (i != tasks.size() - 1) {
                    fWriter.write("\n");
                }
            }
            fWriter.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
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
                String from = s.substring(s.indexOf("/") + 6, s.lastIndexOf("/") - 1);
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

    public static int loadSaved(String s, ArrayList<Task> tasks, int taskCounter) {
        if (s.substring(0, 1).equals("T")) {
            Todo newTodo = new Todo(s.substring(8));
            if (s.substring(4, 5).equals("1")) {
                newTodo.toggleMarked();
            }
            tasks.add(newTodo);

        } else if (s.substring(0, 1).equals("D")) {
            String marked = s.substring(4, 5);
            String task = s.substring(8);
            Deadline newDeadline = new Deadline(task.substring(0, task.indexOf("|") - 1),
                    task.substring(task.indexOf("|") + 2));
            if (marked.equals("1")) {
                newDeadline.toggleMarked();
            }
            tasks.add(newDeadline);

        } else {
            String marked = s.substring(4, 5);
            String task = s.substring(8);
            String desc = task.substring(0, task.indexOf("|") - 1);
            task = task.substring(task.indexOf("|") + 2);
            Event newEvent = new Event(desc, task.substring(0, task.indexOf("|") - 1),
                    task.substring(task.indexOf("|") + 2));
            tasks.add(newEvent);
        }
        return taskCounter + 1;
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

    public String getDescription() {
        return description;
    }

    public String toSavedString() {
        return this.isDone ? "1 | " + this.description : "0 | " + this.description;
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
    public String toSavedString() {
        return "T | " + super.toSavedString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MM-yyyy")
                .toFormatter(Locale.ENGLISH);
        this.by = LocalDate.parse(by, df);
    }

    @Override
    public String toSavedString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy").withLocale(Locale.ENGLISH);
        return "D | " + super.toSavedString() + " | " + this.by.format(df);
    }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy").withLocale(Locale.ENGLISH);
        return "[D]" + super.toString() + " (by: " + by.format(df) + ")";
    }
}

class Event extends Task {
    protected LocalDateTime from;
    protected LocalTime to;

    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd-MM-yyyy HH:mm")
                .toFormatter(Locale.ENGLISH);
        DateTimeFormatter df2 = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("HH:mm")
                .toFormatter(Locale.ENGLISH);
        this.from = LocalDateTime.parse(from, df);
        this.to = LocalTime.parse(to, df2);
    }

    @Override
    public String toSavedString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").withLocale(Locale.ENGLISH);
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("HH:mm").withLocale(Locale.ENGLISH);

        return "E | " + super.toSavedString() + " | " + this.from.format(df) + " | " + this.to.format(df2);
    }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").withLocale(Locale.ENGLISH);
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("HH:mm").withLocale(Locale.ENGLISH);
        return "[E]" + super.toString() + " (from: " + from.format(df) + " to: " + to.format(df2) + ")";
    }

}

class DukeException extends Exception {
    public DukeException(String message) {

        super(message);
    }
}