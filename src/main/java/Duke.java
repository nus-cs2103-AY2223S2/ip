import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        
        /**
         * allTasks: the file that constains all the tasks
         * taskFolder: the folder taht contains allTasks
         */
        File allTasks = new File("data/tasks.txt");
        File taskFolder = new File("data");
        ArrayList<Task> tasks = new ArrayList<Task>();

        /**
         * print different starting messages depending on whether allTasks and/or
         * taskFolder exist
         */
        if (!taskFolder.exists()) {
            printToFormat("The default Task Folder is not found, creating data folder with task file...");
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
            printToFormat("The default tasks do not exist, creating default task file...");
            File f = new File(taskFolder, "task.txt");
            try {
                f.createNewFile();
                System.out.printf("---Task File created successfully\n---ready to create tasks\n");
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        } else {
            try {
                loadDefaultTasks(tasks, allTasks);
            } catch (FileNotFoundException e) {
                System.out.println("Could not load the default tasks: " + e.getMessage());
            }
            printToFormat("---Default Task List successfully loaded");
        }



        while (sc.hasNext()) {
            int byeIndicator = 0;
            try {
                /**
                 * @param inputLine: a String that is the command entered by the user
                 * @param words[]: an array whose elements are from inputline separated by
                 * a space. used to determine which command is entered
                 */
                String inputLine = sc.nextLine();
                DukeException.checkInput(inputLine);
                String words[] = inputLine.split(" ");
                Command command = Command.valueOf(words[0].toUpperCase());
                switch (command) {
                    case BYE: 
                        byeIndicator = 1;
                        printToFormat("    Bye, have a nice day!");
                        break;
                    case LIST:
                    /**
                     * loop through all tasks in the arraylist and print out each task
                     */
                        StringBuilder sb = new StringBuilder();
                        for (int i = 1; i <= tasks.size(); i++) {
                            sb.append("    " + i + ". " + tasks.get(i - 1) + "\n");
                        }
                        printToFormat(sb.toString());
                        break;
                    case MARK:
                    /**
                     * change the specified task's status to "[X]"
                     */
                        int ind = Integer.parseInt(words[1]);
                    /**
                     * prints error message if the index is too large
                     */
                        if (ind > tasks.size()) {
                            printToFormat("    This task does not exist");
                        } else {
                            Task updatedTask = tasks.get(ind - 1);
                            tasks.get(ind - 1).mark();
                            printToFormat("    Congrats on completing the following task:\n    " + updatedTask);
                        }
                        break;
                    case UNMARK:
                    /**
                     * changed the specified task's staus to "[ ]"
                     */
                        int ind1 = Integer.parseInt(words[1]);
                        if (ind1 > tasks.size()) {
                            printToFormat("    This task does not exist");
                        } else {
                            Task updatedTask = tasks.get(ind1 - 1);
                            tasks.get(ind1 - 1).unmark();
                            printToFormat("    Unchecked the following task:\n    " + updatedTask);
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
                        String[] parts1 = inputLine.split("/");
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
                        printToFormat("    The following task is removed:\n    " + tasks.remove(Integer.parseInt(words[1]) - 1));
                        break;
                    }
                    try {
                        updateAllTasks(tasks, allTasks);
                    } catch (IOException e) {
                        System.out.println("local update failed: " + e.getMessage());
                    }
            } catch (DukeException e) {
                /**
                 * prints out the error message if an error is caught
                 */
                printToFormat("    " + e.getMessage());
            }
            if (byeIndicator == 1) {
                break;
            }
        }
    }

    /**
     * method to print messages in desired format with a starting line and an ending line
     * @param message a string describing the message
     */
    private static void printToFormat(String message) {
        String lineBreak1 = "-->-->-->-->-->-->-->-->-->-->-->\n";
        String lineBreak2 = "\n<--<--<--<--<--<--<--<--<--<--\n\n";
        System.out.println(lineBreak1 + message + lineBreak2);
    }

    private static void loadDefaultTasks(ArrayList<Task> tasks, File file) throws FileNotFoundException {
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
    }

    private static void updateAllTasks(ArrayList<Task> tasks, File allTasks) throws IOException {
        FileWriter fw = new FileWriter(allTasks);
        for (Task task : tasks) {
            fw.write(task.toStoreFormatString() + System.lineSeparator());
        }
        fw.close();
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
/**
 * Creates a Deadline class that inherits from Task
 * to handle deadline tasks
 */
class Deadline extends Task {
    protected final String deadline;
    /**
     * @param name: a string indicating the name of the task
     * @param status: a String indicating whether the task is done or not
     * @param deadline: a string indicating the deadline of the task
     */
    Deadline (String name, int status, String deadline) {
        super(name, status);
        this.deadline = deadline;
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
        return "[D]" + status + " " + name + "(" + deadline + ")";
    }
    
    public String toStoreFormatString() {
        return String.format("D/%s/%d/%s", super.name, this.getStatusNo(), deadline);
    }
}

class Event extends Task {
    protected final String from;
    protected final String to;
    /**
     * 
     * @param name: a string indicating thename of the Event task
     * @param status: a String that checks if the Event is done or not
     * @param from: a string representing the starting time passed in by the user
     * @param to: a string representing the ending time passed in by the user
     */
    Event(String name, int status, String from, String to) {
        super(name, status);
        this.from = from;
        this.to = to;
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
        return "[E]" + status + " " + name + "(" + from + to + ")";
    }
    
    public String toStoreFormatString() {
        return String.format("E/%s/%d/%s/%s", super.name, this.getStatusNo(), from, to);
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