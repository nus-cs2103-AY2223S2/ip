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
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        ui.takeCommands(this.tasks);
        storage.saveData(this.tasks);
    }
    public static void main(String[] args) {
        new Duke("../data/duke.txt").run();
//
    }

    // handleCommand takes in command String s, current tasks, and current number of tasks
    // updates task array and returns taskCounter

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

class Storage {
    private String filePath;

    public Storage(String s) {
        this.filePath = s;
    }

    public ArrayList<Task> load() {
        System.out.println("    . . . Loading . . . ");
        File file = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner saveFile = new Scanner(file);
            System.out.println("    Saved data found, welcome back!");
            while (saveFile.hasNextLine()) {
                loadSaved(saveFile.nextLine(), tasks);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("    No saved data not found, new file will be created");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return tasks;
    }

    public void saveData(TaskList t) {
        ArrayList<Task> tasks = t.getTasks();
        try {
            FileWriter fWriter = new FileWriter(this.filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fWriter.write(task.toSavedString());
                if (i != tasks.size() - 1) {
                    fWriter.write("\n");
                }
            }
            fWriter.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public static void loadSaved(String s, ArrayList<Task> tasks) {
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
    }
}

class Ui {

    public Ui() {

    }

    public void showWelcome() {
        System.out.println("    Hi! I'm Samantha\n    How can I help?");
    }

    public void takeCommands(TaskList t) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        ArrayList<Task> tasks = t.getTasks();
        int taskCounter = tasks.size();
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

        System.out.println("    Bye. Hope to see you soon!");
    }

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
}

class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> t) {
        this.tasks = t;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}

class DukeException extends Exception {
    public DukeException(String message) {

        super(message);
    }
}