import java.util.Scanner;
import java.util.Arrays;
public class Duke {

    public static void main(String[] args) {
        Task[] allTasks = new Task[100];
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi, I'm Nero and I am an automated chat bot" + "\n" + "What would you like to do?");
        while (true) {
            String[] input = sc.nextLine().split(" ");
            if (input[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!" + "\n");
                break;
            } else if (input[0].equals("list")) {
                System.out.println("Here are all your tasks: ");
                for (int i = 0; i < counter; i++) {
                    int index = i + 1;
                    Task currTask = allTasks[i];
                    System.out.println(index + ". " + currTask.getStatusIcon() + " " + currTask.getDescription());
                }
            } else if (input[0].equals("mark")) {
                int taskToMark = Integer.parseInt(input[1]) - 1;
                Task currTask = allTasks[taskToMark];
                currTask.markAsDone();
                System.out.println("Great job on completing this task! " + "\n" + currTask.getStatusIcon() + " " + currTask.getDescription());
            } else if (input[0].equals("unmark")) {
                int taskToUnmark = Integer.parseInt(input[1]) - 1;
                Task currTask = allTasks[taskToUnmark];
                currTask.markAsUndone();
                System.out.println("Remember to complete this task!! " + "\n" + currTask.getStatusIcon() + " " + currTask.getDescription());
            } else {
                String toAdd = "";
                for (int i = 0; i < input.length; i++) {
                    toAdd += input[i] + " ";
                }
                Task newTask = new Task(toAdd);
                allTasks[counter] = newTask;
                counter++;
                System.out.println(newTask.getDescription() + "\n");
            }
        }
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

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
