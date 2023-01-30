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

    public Duke(String filePath, String folderPath) {
        ui = new Ui();
        storage = new Storage(filePath, folderPath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String inputLine = ui.readCommand();
                Command c = Parser.parse(inputLine);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt", "data").run();
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

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        // sc.close();
        return input;
    }

    public void showLoadingError() {
        this.printToFormat("Sorry, default tasks could not be loaded, starting a fresh task list");
    }
    
    public void printToFormat(String message) {
        String lineBreak1 = "-->-->-->-->-->-->-->-->-->-->-->\n    ";
        String lineBreak2 = "\n<--<--<--<--<--<--<--<--<--<--\n\n";
        System.out.println(lineBreak1 + message + lineBreak2);
    }

    public boolean userSaidBye() {
        return byeIndicator;
    }
}

class Storage {
    private File allTasks;
    private File taskFolder;

    Storage(String filePath, String folderPath) {
        this.allTasks = new File(filePath);
        this.taskFolder = new File(folderPath);
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> defaultTasks = new ArrayList<>();
        if (!taskFolder.exists()) {
            System.out.println("---The default Task Folder is not found, creating data folder with task file...");
            taskFolder.mkdir();
            System.out.println("---Task Folder created successfully");
            File f = new File(taskFolder, "task.txt");
            try {
                f.createNewFile();
                System.out.printf("---Task File created successfully\n---ready to create tasks\n");
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        } else if (!allTasks.exists()) {
            System.out.println("The default tasks do not exist, creating default task file...");
            File f = new File(taskFolder, "task.txt");
            try {
                f.createNewFile();
                System.out.printf("---Task File created successfully\n---ready to create tasks\n");
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        } else {
            try {
                defaultTasks = loadDefaultTasks(new ArrayList<Task>(), allTasks);
            } catch (FileNotFoundException e) {
                System.out.println("Could not load the default tasks: " + e.getMessage());
            }
            System.out.println("\n\n---Default Task List successfully loaded\n\n");
        }
        return defaultTasks;
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
        FileWriter fw = new FileWriter(this.allTasks);
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
}

class Parser {
    public static Command parse(String inputLine) throws DukeException {    
        try {
            /**
             * @param inputLine: a String that is the command entered by the user
             * @param words[]: an array whose elements are from inputline separated by
             * a space. used to determine which command is entered
             */
            DukeException.checkInput(inputLine);
            String words[] = inputLine.split(" ");
            CommandType commandtype = CommandType.valueOf(words[0].toUpperCase());
            switch (commandtype) {
                case BYE:
                    return new ByeCommand();
                case LIST:
                    return new ListCommand();
                case MARK:
                /**
                 * change the specified task's status to "[X]"
                 */
                    int taskNoMark = Integer.parseInt(words[1]);
                    return new MarkCommand(taskNoMark);
                case UNMARK:
                /**
                 * changed the specified task's staus to "[ ]"
                 */
                    int taskNoUnmark = Integer.parseInt(words[1]);
                    return new UnmarkCommand(taskNoUnmark);
                case DEADLINE:
                /**
                 * creates and adds a deadline task to the arraylist of all tasks
                 */
                    String[] parts = inputLine.split("/");
                    Deadline task = new Deadline(parts[0].split(" ", 2)[1], 0, parts[1]);
                    return new AddCommand(task);
                case EVENT:
                /**
                 * creates and adds an event task to the arraylist of all tasks
                 */
                    String[] parts1 = inputLine.split(" /");
                    Event event = new Event(parts1[0].split(" ", 2)[1], 0, parts1[1], parts1[2]);
                    return new AddCommand(event);
                case TODO:
                /**
                 * creates and adds a todo task to the arraylist of all tasks
                 */
                    Todo todo = new Todo(inputLine.split(" ", 2)[1], 0);
                    return new AddCommand(todo);
                case DELETE:
                /**
                 * removes the task at the specified index
                 */
                    return new DeleteCommand(Integer.parseInt(words[1]));
                default:
                    throw new DukeException("Not a valid command: " + inputLine);
                }
        } catch (DukeException e) {
            /**
             * prints out the error message if an error is caught
             */
            throw e;
        }
    }

    public static LocalDateTime formatDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        return dateTime;
    }

    public static String reverseFormatDateTime(LocalDateTime input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return input.format(formatter);
    }

    public static String TransformDateTime(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' HH:mm");
        return dateTime.format(outputFormatter);
    }
}

abstract class Command {    
    abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}

class AddCommand extends Command {
    private Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.printToFormat("Task successfully added:\n    " + task);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            System.out.println("failed to update tasks locally: " + e.getMessage());
        }
    }
}

class DeleteCommand extends Command {
    private int taskNo;

    DeleteCommand(int n) {
        this.taskNo = n;
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.printToFormat("The following task is removed:\n    " + tasks.remove(taskNo));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        try {
            storage.update(tasks);
        } catch (IOException e) {
            System.out.println("failed to update tasks locally: " + e.getMessage());
        }
    }
}

class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printToFormat(tasks.toString());
    }
}

class MarkCommand extends Command {
    private int taskNo;

    MarkCommand(int n) {
        this.taskNo = n;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.printToFormat("Marked as completed:\n    " + tasks.mark(taskNo));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        try {
            storage.update(tasks);
        } catch (IOException e) {
            System.out.println("failed to update tasks locally: " + e.getMessage());
        }
    }
}

class UnmarkCommand extends Command {
    private int taskNo;

    UnmarkCommand(int n) {
        this.taskNo = n;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.printToFormat("Marked as yet to complete:\n    " + tasks.unmark(taskNo));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        try {
            storage.update(tasks);
        } catch (IOException e) {
            System.out.println("failed to update tasks locally: " + e.getMessage());
        }
    }
}

class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printToFormat("Bye, have a nice day.");
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
        this.deadline = Parser.formatDateTime(dlString);
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
        return "[D]" + status + " " + name + "(by " + Parser.TransformDateTime(deadline) + ")";
    }
    
    public String toStoreFormatString() {
        return String.format("D/%s/%d/%s", super.name, this.getStatusNo(), Parser.reverseFormatDateTime(deadline));
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
        this.from = Parser.formatDateTime(from);
        this.to = Parser.formatDateTime(to);
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
        return String.format("[E]%s %s (from %s to %s)", status, name, Parser.TransformDateTime(from),
        Parser.TransformDateTime(to));
    }
    
    public String toStoreFormatString() {
        return String.format("E/%s/%d/%s/%s", super.name, this.getStatusNo(), Parser.reverseFormatDateTime(from), Parser.reverseFormatDateTime(to));
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
enum CommandType {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
}