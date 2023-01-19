import java.util.*;
public class Duke {
    private static void printDashes() {
        System.out.println ("****************************************");
    }

    private static void printList(ArrayList<Task> items) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= items.size(); i++) {
            System.out.println(i + "." + items.get(i-1));
        }
    }

    private static void numOfTasks(ArrayList<Task> items) {
        System.out.println("Now you have " + items.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        String request = "";
        int chosenTask;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);

        printDashes();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! This is Esther!");
        System.out.println("How can I help you today? ^_^");
        printDashes();

        while(!request.equals("bye")) {
            String userInput = sc.nextLine();
            request = userInput.split("\\s+")[0];

            switch(request) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon! ^_^");
                    break;
                case "list":
                    printList(list);
                    break;
                case "mark":
                    chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
                    list.get(chosenTask - 1).setIsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + list.get(chosenTask - 1).getStatusIcon() + "] " + list.get(chosenTask - 1).getTaskDes());
                    break;
                case "unmark":
                    chosenTask = Integer.parseInt(userInput.split("\\s+")[1]);
                    list.get(chosenTask - 1).revertIsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + list.get(chosenTask - 1).getStatusIcon() + "] " + list.get(chosenTask - 1).getTaskDes());
                    break;
                case "todo":
                    String todo_descrip = userInput.substring(userInput.indexOf(" ")).trim();
                    Todo todo = new Todo(todo_descrip);
                    list.add(todo);
                    System.out.println("Got it. I've added this task:\n" + todo);
                    numOfTasks(list);
                    break;
                case "deadline":
                    String dd_full = userInput.substring(userInput.indexOf(" ")).trim();
                    String dd_descrip = dd_full.split("/")[0];
                    //String dd_date = dd_full.substring(dd_full.lastIndexOf(" ")).trim();
                    String dd_date = dd_full.split("/")[1].substring(dd_full.split("/")[1].indexOf(" ")).trim();
                    Deadline deadline = new Deadline(dd_descrip, dd_date);
                    list.add(deadline);
                    System.out.println("Got it. I've added this task:\n" + deadline);
                    numOfTasks(list);
                    break;
                case "event":
                    String event_full = userInput.substring(userInput.indexOf(" ")).trim();
                    String event_descrip = event_full.split("/")[0];
                    String event_from = event_full.split("/")[1].substring(event_full.split("/")[1].indexOf(" ")).trim();
                    String event_to = event_full.split("/")[2].substring(event_full.split("/")[2].indexOf(" ")).trim();
                    Event event = new Event(event_descrip, event_from, event_to);
                    list.add(event);
                    System.out.println("Got it. I've added this task:\n" + event);
                    numOfTasks(list);
                    break;
                default:
                    System.out.println("*added: " + userInput);
                    Task userTask = new Task(userInput);
                    list.add(userTask);
            }
            printDashes();
        }
    }
}

class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setIsDone() {
        isDone = true;
    }

    public void revertIsDone() {
        isDone = false;
    }

    public String getTaskDes() {
        return taskDescription;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getTaskDes();
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
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}

class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }
}


