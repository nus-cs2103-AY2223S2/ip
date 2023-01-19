import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        List<Task> taskList = new ArrayList<Task>();
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm DonkeyChat!\nWhat can I do for you?");
        boolean isRunning = true;
        while (isRunning) {
            String currInput = input.nextLine();
            String[] splitInput = currInput.split(" ");
            String currCommand = splitInput[0];
            Integer taskIndex;
            switch (currCommand) {
                case "bye":
                    System.out.println("Adios!");
                    isRunning = false;
                    break;
                case "list":
                    for (int i = 0; i < taskList.size(); i++) {
                        Task task = taskList.get(i);
                        System.out.println(i + 1 + "." + "[" + task.getStatusIcon() + "] " + task);
                    }
                    break;
                case "mark":
                    try {
                        taskIndex = Integer.valueOf(splitInput[1]) - 1;
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        break;
                    }
                    if (taskIndex < 0 || taskIndex > taskList.size()) {
                        System.out.println("invalid task index!");
                        break;
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    Task task = taskList.get(taskIndex);
                    task.setDone(true);
                    System.out.println("[" + task.getStatusIcon() + "] " + task);
                    break;
                case "unmark":
                    try {
                        taskIndex = Integer.valueOf(splitInput[1]) - 1;
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        break;
                    }
                    if (taskIndex < 0 || taskIndex > taskList.size()) {
                        System.out.println("invalid task index!");
                        break;
                    }
                    System.out.println("OK, I've marked this task as not done yet:");
                    task = taskList.get(taskIndex);
                    task.setDone(false);
                    System.out.println("[" + task.getStatusIcon() + "] " + task);
                    break;
                default:
                    System.out.println("added: " + currInput);
                    taskList.add(new Task(currInput));
                    break;
            }
        }
    }
}
