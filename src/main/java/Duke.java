import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> dukeList = new ArrayList<>();
    private static String[] taskCreationCommands = {"todo", "deadline", "event"};

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
                Optional<Command> cmd = Command.getCommand(line);
                if (cmd.isPresent()) { 
                    Command command = cmd.get();
                    if (command == Command.LIST) {
                        displayMsg(outputList());
                    } else if (command == Command.MARK || command == Command.UNMARK) {
                        int listIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                        Task targetTask = dukeList.get(listIndex);
                        String output; 
                        if (command == Command.MARK){
                            targetTask.markDone();
                            output = "Nice! I've marked this task as done:";
                        } else {
                            targetTask.unmarkDone();
                            output = "Ok, I've marked this task as not done yet:";
                        }
                        displayMsg(output + "\n" + indentString(targetTask.toString(), 1));
                    } else if (command == Command.DELETE) {
                        Task removedTask = removeTask(line);
                        displayMsg("Noted. I've removed this task:\n" + indentString(removedTask.toString(), 1) + "\n" + countTasks());
                    } else {  // a task creation command being called
                        Task newTask;
                        try {
                            if (line.startsWith("event")) {
                                newTask = Event.create(line);
                            } else if (line.startsWith("deadline")) {
                                newTask = Deadline.create(line);
                            } else {
                                newTask = ToDo.create(line);
                            } 
                            dukeList.add(newTask);
                            StringBuilder output = new StringBuilder();
                            output.append("Got it. I've added this task:\n" + indentString(newTask.toString(), 1) + "\n" + countTasks());
                            displayMsg(output.toString());
                        } catch (TaskInitError e) {
                            displayMsg("OOPS!!! " + e.getMessage());
                        }
                    
                    } 
                } else {
                    displayMsg("OOPS!!! I'm sorry, but I don't know what that means :-(");
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

    public static Task removeTask(String command) {
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        return dukeList.remove(index);
    }

    public static String countTasks() {
        return "Now you have " + dukeList.size() + " task" + (dukeList.size() == 1 ? "" : "s") + " in the list.";
    }
}

enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list");

    private String commandString;

    Command(String commandLine) {
        this.commandString = commandLine;
    }

    public static Optional<Command> getCommand(String commandLine) {
        String commandInput = commandLine.split(" ")[0];
        return Arrays.stream(Command.values()).filter(cmd -> cmd.commandString.equals(commandInput)).findFirst();
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
    public static ToDo create(String command) throws TaskNameNotSpecified {
        try {
            return command.indexOf("todo") == -1 ? new ToDo(command) : new ToDo(command.substring(5));
        } catch (StringIndexOutOfBoundsException e) {
            throw new TaskNameNotSpecified("ToDo description cannot be empty.");
        }
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
    public static Deadline create(String command) throws TaskNameNotSpecified, DeadlineByNotSpecified {
        int indexOfBy = command.indexOf("/by");
        if (indexOfBy == -1) {
            throw new DeadlineByNotSpecified("Deadline task requires keyword '/by'");
        } 

        String taskName = command.substring(9, indexOfBy);
        if (taskName.equals("")) {
            throw new TaskNameNotSpecified("Deadline description canont be empty.");
        }

        try {
            String dueDate = command.substring(indexOfBy + 4);
            if (dueDate.equals("")) {
                throw new DeadlineByNotSpecified("Due date field cannot be empty");
            }
            return new Deadline(taskName, dueDate);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DeadlineByNotSpecified("Due date field cannot be empty.");
        }

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

    public static Event create(String command) throws TaskNameNotSpecified, EventFromToNotSpecified {
        command = command.substring(6);
        
        int indexOfFrom = command.indexOf("/from");
        int indexOfTo = command.indexOf("/to");
        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new EventFromToNotSpecified("Event task requires keywords '/from' and '/to'");
        }

        String taskName = command.substring(0, indexOfFrom);
        if (taskName.equals("")) {
            throw new TaskNameNotSpecified("The description of an event cannot be empty.");
        }

        String fromDate = command.substring(indexOfFrom + 6, indexOfTo);
        if (fromDate.equals("")) {
            throw new EventFromToNotSpecified("from/to fields cannot be empty.");
        }

        try {
            String toDate = command.substring(indexOfTo + 4, command.length());
            return new Event(taskName, fromDate, toDate);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EventFromToNotSpecified("from/to fields cannot be empty.");
        }
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

class TaskInitError extends Exception {
    public TaskInitError(String errorMessage) {
        super(errorMessage);
    }
}

class EventFromToNotSpecified extends TaskInitError {
    public EventFromToNotSpecified(String errorMessage) {
        super(errorMessage);
    }
}

class DeadlineByNotSpecified extends TaskInitError {
    public DeadlineByNotSpecified(String errorMessage) {
        super(errorMessage);
    }
}

class TaskNameNotSpecified extends TaskInitError {
    public TaskNameNotSpecified(String errorMessage) {
        super(errorMessage);
    }
}