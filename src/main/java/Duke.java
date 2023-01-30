import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            ui.handleInput(sc.nextLine(), tasks);
            if (ui.userSaidBye()) {
                sc.close();
                break;
            }
            try {
                storage.update(tasks);
            } catch (IOException e) {
                System.out.println("local update failed: " + e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}


class Ui {
    private String welcomeMessage = "Hello from\n" + 
    " ____        _        \n" +
    "|  _ \\ _   _| | _____ \n" +
    "| | | | | | | |/ / _ \\\n" +
    "| |_| | |_| |   <  __/\n" +
    "|____/ \\__,_|_|\\_\\___|\n";
    private boolean byeIndicator = false;

    public void welcome() {
        System.out.println(welcomeMessage);
    }

    public void showLoadingError() {
        printToFormat("Sorry, default tasks could not be loaded, starting a fresh task list");
    }
    
    private static void printToFormat(String message) {
        String lineBreak1 = "-->-->-->-->-->-->-->-->-->-->-->\n    ";
        String lineBreak2 = "\n<--<--<--<--<--<--<--<--<--<--\n\n";
        System.out.println(lineBreak1 + message + lineBreak2);
    }

    public boolean userSaidBye() {
        return byeIndicator;
    }

    public void handleInput(String inputLine, TaskList tasks) {
        try {
            /**
             * @param inputLine: a String that is the command entered by the user
             * @param words[]: an array whose elements are from inputline separated by
             * a space. used to determine which command is entered
             */
            DukeException.checkInput(inputLine);
            String words[] = inputLine.split(" ");
            Command command = Command.valueOf(words[0].toUpperCase());
            switch (command) {
                case BYE:
                    printToFormat("    Bye, have a nice day!");
                    this.byeIndicator = true;
                    break;
                case LIST:
                    printToFormat(tasks.toString());
                    break;
                case MARK:
                /**
                 * change the specified task's status to "[X]"
                 */
                    int taskNoMark = Integer.parseInt(words[1]);
                    try {
                        printToFormat("Marked ask completed:\n   " + tasks.mark(taskNoMark));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case UNMARK:
                /**
                 * changed the specified task's staus to "[ ]"
                 */
                    int taskNoUnmark = Integer.parseInt(words[1]);
                    try {
                        printToFormat("Marked ask completed:\n   " + tasks.mark(taskNoUnmark));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DEADLINE:
                /**
                 * creates and adds a deadline task to the arraylist of all tasks
                 */
                    String[] parts = inputLine.split("/");
                    Deadline task = new Deadline(parts[0].split(" ", 2)[1], 0, parts[1]);
                    tasks.add(task);
                    printToFormat("    Successfully added the following task:\n    " + task);
                    break;
                case EVENT:
                /**
                 * creates and adds an event task to the arraylist of all tasks
                 */
                    String[] parts1 = inputLine.split(" /");
                    Event event = new Event(parts1[0].split(" ", 2)[1], 0, parts1[1], parts1[2]);
                    tasks.add(event);
                    printToFormat("    Successfully added the following task:\n    " + event);
                    break;
                case TODO:
                /**
                 * creates and adds a todo task to the arraylist of all tasks
                 */
                    Todo todo = new Todo(inputLine.split(" ", 2)[1], 0);
                    tasks.add(todo);
                    printToFormat("    Successfully added the following task:\n    " + todo);
                    break;
                case DELETE:
                /**
                 * removes the task at the specified index
                 */
                    printToFormat("    The following task is removed:\n    " + tasks.remove(Integer.parseInt(words[1])));
                    break;
                }
        } catch (DukeException e) {
            /**
             * prints out the error message if an error is caught
             */
            printToFormat("    " + e.getMessage());
        }
    } 
}

class Storage {
    private File defaultTasks;

    Storage(String filePath) {
        this.defaultTasks = new File(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        
        try {
            return loadDefaultTasks(new ArrayList<Task>(), defaultTasks);
        } catch (FileNotFoundException e) {
            throw new DukeException("Default Tasks not found");
        }
    }

    private static ArrayList<Task> loadDefaultTasks(ArrayList<Task> tasks, File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while(s.hasNext()) {
            String[] lineArr = s.nextLine().split("/");
            switch (lineArr[0]) {
                case "D":
                    tasks.add(new Deadline(lineArr[1], Integer.parseInt(lineArr[2]), lineArr[3]));
                    break;
                case "T":
                    tasks.add(new Todo(lineArr[1], Integer.parseInt(lineArr[2])));
                    break;
                case "E":
                    tasks.add(new Event(lineArr[1], Integer.parseInt(lineArr[2]), lineArr[3], lineArr[4]));
                    break;
            }
        }
        s.close();
        return tasks;
    }

    public void update(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.defaultTasks);
        fw.write(tasks.getWriteString());
        fw.close();
    }
}

class TaskList {
    private ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public String toString() {
        /**
         * loop through all tasks in the arraylist and print out each task
         */
        StringBuilder sb = new StringBuilder();
        sb.append("1. " + tasks.get(0) + "\n");
        for (int i = 2; i <= tasks.size(); i++) {
            sb.append("    " + i + ". " + tasks.get(i - 1) + "\n");
        }
        return sb.toString();
    }

    public Task mark(int taskNumber) throws DukeException {
        try {
            tasks.get(taskNumber - 1).mark();
        } catch (Exception e) {
            throw new DukeException("Task does not exist, current number of tasks: " + tasks.size());
        }
        return tasks.get(taskNumber - 1);
    }

    public Task unmark(int taskNumber) throws DukeException {
        try {
            tasks.get(taskNumber - 1).unmark();
        } catch (Exception e) {
            throw new DukeException("Task does not exist, current number of tasks: " + tasks.size());
        }
        return tasks.get(taskNumber - 1);
    }

    public Task remove(int taskNumber) throws DukeException {
        try {
            return tasks.remove(taskNumber - 1);
        } catch (Exception e) {
            throw new DukeException("Task does not exist, current number of tasks: " + tasks.size());
        }
    }

    public String getWriteString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.toStoreFormatString() + System.lineSeparator());
        }
        return sb.toString();
    }
}

/**
 * Creates a Task class to handle different tasks
 */

 class Task {
    /**
     * @param name: a string indicating the name of the task
     * @param status: a boolean indicating whether the task is done or not
     */
    protected String name;
    protected String status;

    public Task(String name, int status) {
        this.name = name;
        if (status == 1) {
            this.status = "[X]";
        } else {
            this.status = "[ ]";
        }
    }
    /**
     * overrides the toString method
     */
    public String toString() {
        return "." + status + " " + name;
    }
    /**
     * method to update a task as done.
     * @return a new Task with status being true
     */
    public void mark() {
        this.status = "[X]";
    }
    /**
     * method to update a task as undone
     * @return a new Task with status being false
     */
    public void unmark() {
        this.status = "[ ]";
    }

    public String toStoreFormatString() {
        return "";
    }
    protected static LocalDateTime formatDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        return dateTime;
    }

    protected static String reverseFormatDateTime(LocalDateTime input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return input.format(formatter);
    }

    protected static String TransformDateTime(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' HH:mm");
        return dateTime.format(outputFormatter);
    }
}
/**
 * Creates a Deadline class that inherits from Task
 * to handle deadline tasks
 */
class Deadline extends Task {
    protected final LocalDateTime deadline;
    /**
     * @param name: a string indicating the name of the task
     * @param status: a String indicating whether the task is done or not
     * @param deadline: a string indicating the deadline of the task
     */
    Deadline (String name, int status, String dlString) {
        super(name, status);
        this.deadline = formatDateTime(dlString);
    }

    private int getStatusNo() {
        if (super.status.equals("[ ]")) {
            return 0;
        } else {
            return 1;
        }
    }
    /**
     * overrides the toString method
     */
    public String toString() {
        return "[D]" + status + " " + name + "(by " + TransformDateTime(deadline) + ")";
    }
    
    public String toStoreFormatString() {
        return String.format("D/%s/%d/%s", super.name, this.getStatusNo(), reverseFormatDateTime(deadline));
    }
}

class Event extends Task {
    protected final LocalDateTime from;
    protected final LocalDateTime to;
    /**
     * 
     * @param name: a string indicating thename of the Event task
     * @param status: a String that checks if the Event is done or not
     * @param from: a string representing the starting time passed in by the user
     * @param to: a string representing the ending time passed in by the user
     */
    Event(String name, int status, String from, String to) {
        super(name, status);
        this.from = formatDateTime(from);
        this.to = formatDateTime(to);
    }

    private int getStatusNo() {
        if (super.status.equals("[ ]")) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * overrrides toString method
     */
    public String toString() {
        return String.format("[E]%s %s (from %s to %s)", status, name, TransformDateTime(from), 
        TransformDateTime(to));
    }
    
    public String toStoreFormatString() {
        return String.format("E/%s/%d/%s/%s", super.name, this.getStatusNo(), reverseFormatDateTime(from), reverseFormatDateTime(to));
    }
}

class Todo extends Task {
    /**
     * 
     * @param name: a String indicating the name of the todo task
     * @param status: a String indicating whether the task is done
     */
    Todo(String name, int status) {
        super(name, status);
    }

    private int getStatusNo() {
        if (super.status.equals("[ ]")) {
            return 0;
        } else {
            return 1;
        }
    }
    /**
     * overrides toString method
     */
    public String toString() {
        return "[T]" + status + " " + name;
    }

    public String toStoreFormatString() {
        return String.format("T/%s/%d", super.name, this.getStatusNo());
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /**
     * 
     * @param inputLine: a line of command entered by the user, to be checked if it is valid
     * @throws DukeException: throws a customised exception message if the command input is 
     * not valid.
     */
    public static void checkInput(String inputLine) throws DukeException {
        if (inputLine.equals("deadline")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (inputLine.equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (inputLine.equals("event")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else if (inputLine.equals("delete")) {
            throw new DukeException("☹ OOPS!!! Please specify which task to delete.");
        } 
    }
}

/**
 * a list of valid Commands as enum
 */
enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
}