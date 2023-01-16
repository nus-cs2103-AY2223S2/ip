import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> dukeList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
                
        Scanner sc = new Scanner(System.in);
        String line = "init";
        // System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
        while (!line.equals("bye")) {
            if (!line.equals("init")) {
                if (line.equals("list")) {
                    displayMsg(outputList());
                } else if (line.startsWith("mark ") || line.startsWith("unmark ")){
                    int listIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                    Task targetTask = dukeList.get(listIndex);
                    String output; 
                    if (line.startsWith("mark")){
                        targetTask.markDone();
                        output = "Nice! I've marked this task as done:";
                    } else {
                        targetTask.unmarkDone();
                        output = "Ok, I've marked this task as not done yet:";
                    }
                    displayMsg(output + "\n" + indentString(targetTask.toString(), 1));
                } else {
                    Task newTask;
                    if (line.startsWith("event")) {
                        newTask = Event.create(line);
                    } else if (line.startsWith("deadline")) {
                        newTask = Deadline.create(line);
                    } else {
                        newTask = ToDo.create(line);
                    }
                    dukeList.add(newTask);
                    StringBuilder output = new StringBuilder();
                    output.append("Got it. I've added this task:\n" + indentString(newTask.toString(), 1) + "\n" + "Now you have " + dukeList.size());
                    output.append(" task" + (dukeList.size() == 1 ? "" : "s") + " in the list.");
                    displayMsg(output.toString());
                }
                System.out.println("");   
            }
            line = sc.nextLine();
        }
        sc.close();
        displayMsg("Bye. Hope to see you again soon!");
    }


    public static String outputList() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:");
        for (int index = 0; index < dukeList.size(); index++) {
            result.append("\n" + (index + 1) + ". " + dukeList.get(index).toString());
        }
        return result.toString();
    }

    public static void displayMsg(String msg) {
        System.out.println(indentString(wrapMessageBorder(msg), 1));
    }

    public static String wrapMessageBorder(String msg) {
        String border = "____________________________________________________________";
        return border + "\n" + msg + "\n" + border;
    }

    public static String indentString(String msg, int indendationLevel) {
        String indent = "  " .repeat(indendationLevel);
        String[] lines = msg.split("\n");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            result.append(indent + lines[i] + (i + 1 < lines.length ? "\n" : ""));
        }
        return result.toString();
    }
}

abstract class Task {
    String type;
    String taskName;
    boolean completed = false;

    public Task(String taskName, String type) {
        this.taskName = taskName;
        this.type = type;
    }

    public void markDone() {
        this.completed = true;
    } 
    public void unmarkDone() {
        this.completed = false;
    }

    public abstract String stringFields(); 

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + (this.completed ? "x" : " ") + "] " + this.taskName + this.stringFields();
    }
}

class ToDo extends Task {

    // Factory method
    public static ToDo create(String command) {
        return command.indexOf("todo") == -1 ? new ToDo(command) : new ToDo(command.substring(5));
    }
    
    public ToDo(String taskName) {
        super(taskName, "T");
    }

    @Override
    public String stringFields() {
        return "";
    }
}

class Deadline extends Task {
    String dueDate;

    // Factory method
    public static Deadline create(String command) {
        String[] commandObjects = command.substring(9).split(" /by ");
        return new Deadline(commandObjects[0], commandObjects[1]);
    }
    
    public Deadline(String taskName, String dueDate) {
        super(taskName, "D");
        this.dueDate = dueDate;
    }

    @Override
    public String stringFields() {
        return " (by: " + dueDate + ")"; 
    }
}

class Event extends Task {
    String fromDate;
    String toDate;

    public static Event create(String command) {
        command = command.substring(6);
        int indexOfFrom = command.indexOf(" /from ");
        int indexOfTo = command.indexOf(" /to ");
        String taskName = command.substring(0, indexOfFrom);
        String fromDate = command.substring(indexOfFrom + 7, indexOfTo);
        String toDate = command.substring(indexOfTo + 5, command.length());
        return new Event(taskName, fromDate, toDate);
    }

    public Event(String taskName, String fromDate, String toDate) {
        super(taskName, "E");
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String stringFields() {
        return " (from: " + this.fromDate + " to: " + toDate + ")";
    }
}