import java.util.Scanner;
import java.util.Arrays;
public class Duke {

    public static void main(String[] args) {
        Task[] allTasks = new Task[100];
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi, I'm Nero and I am an automated chat bot" + "\n" + "What would you like to do?");
        while (sc.hasNextLine()) {
            String originalString = sc.nextLine();
            String[] input = originalString.split(" ");
            if (input[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!" + "\n");
                break;
            } else if (input[0].equals("list")) {
                System.out.println("Here are all your tasks: ");
                for (int i = 0; i < counter; i++) {
                    int index = i + 1;
                    Task currTask = allTasks[i];
                    System.out.println(index + ". " + currTask.currToPrint());
                }
            } else if (input[0].equals("mark")) {
                int taskToMark = Integer.parseInt(input[1]) - 1;
                Task currTask = allTasks[taskToMark];
                currTask.markAsDone();
                System.out.println("Great job on completing this task! " + "\n" + currTask.currToPrint());
            } else if (input[0].equals("unmark")) {
                int taskToUnmark = Integer.parseInt(input[1]) - 1;
                Task currTask = allTasks[taskToUnmark];
                currTask.markAsUndone();
                System.out.println("Remember to complete this task!! " + "\n" + currTask.currToPrint());
            } else if (input[0].equals("todo")) {
                String toAdd = "";
                for (int i = 1; i < input.length; i++) {
                    toAdd += input[i] + " ";
                }
                Task newTask = new ToDo(toAdd);
                allTasks[counter] = newTask;
                counter++;
                System.out.println("Got it! I've added this task to the list!" + "\n" + newTask.currToPrint() + "\n" + "Now you have " + counter +
                        " tasks in the list!" + "\n");
            } else if (input[0].equals("deadline")) {
                String[] splitString = originalString.split("/");
                String description = splitString[0].replace("deadline", "");
                String duration = splitString[1].replace("by", "by:");
                Task newTask = new Deadline(description, duration);
                allTasks[counter] = newTask;
                counter++;
                System.out.println("Got it! I've added this task to the list!" + "\n" + newTask.currToPrint() + "\n" + "Now you have " + counter +
                        " tasks in the list!" + "\n");
            } else if (input[0].equals("event")) {
                String[] splitString = originalString.split("/");
                String description = splitString[0].replace("event", "");
                String duration = splitString[1].replace("from", "from:") + splitString[2].replace("to", "to:");
                Task newTask = new Event(description, duration);
                allTasks[counter] = newTask;
                counter++;
                System.out.println("Got it! I've added this task to the list!" + "\n" + newTask.currToPrint() + "\n" + "Now you have " + counter +
                        " tasks in the list!" + "\n");
            }
        }
    }
}

abstract class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String currToPrint();
    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone(){
        isDone = false;
    }


}

class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getTaskIcon() {
        return "[T]";
    }

    public String currToPrint() {
        return this.getTaskIcon() + this.getStatusIcon() + " " + this.getDescription();
    }
}

class Deadline extends Task {

    public String duration;
    public Deadline(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }
    public String getTaskIcon() {
        return "[D]";
    }

    public String currToPrint() {
        return this.getTaskIcon() + this.getStatusIcon() + " " + this.getDescription() +
                " (" + this.getDuration() + ")";
    }

}

class Event extends Task {

    public String duration;
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }
    public String getTaskIcon() {
        return "[E]";
    }

    public String currToPrint() {
        return this.getTaskIcon() + this.getStatusIcon() + " " + this.getDescription() +
                " (" + this.getDuration() + ")";
    }

}